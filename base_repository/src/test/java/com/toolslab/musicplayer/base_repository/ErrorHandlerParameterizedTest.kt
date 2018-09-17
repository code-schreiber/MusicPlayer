package com.toolslab.musicplayer.base_repository

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.toolslab.musicplayer.base_repository.exception.*
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldEqual
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.HttpURLConnection.*
import kotlin.reflect.KClass

@RunWith(Parameterized::class)
class ErrorHandlerParameterizedTest(private val input: Exception, private val expected: KClass<*>) {

    private val underTest = ErrorHandler()

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data() = listOf(
                arrayOf(IOException(), NoConnectionException::class),

                arrayOf(httpExceptionWithCode(HTTP_NOT_FOUND), NotFoundException::class),
                arrayOf(httpExceptionWithCode(HTTP_UNAUTHORIZED), UnauthorizedException::class),
                arrayOf(httpExceptionWithCode(HTTP_FORBIDDEN), ForbiddenException::class),
                arrayOf(httpExceptionWithCode(HTTP_NOT_IMPLEMENTED), RepositoryException::class),

                arrayOf(Exception(), RepositoryException::class)
        )

        private val mockResponse: Response<*> = mock()

        private fun httpExceptionWithCode(code: Int): HttpException {
            whenever(mockResponse.code()).thenReturn(code)
            return HttpException(mockResponse)
        }
    }

    @Test
    fun handle() {
        val testObserver = underTest.handle<Any>(input).test()
        testObserver.awaitTerminalEvent()

        testObserver.apply {
            valueCount() shouldEqual 0
            errorCount() shouldEqual 1
            val error = errors()[0]
            error shouldBeInstanceOf expected
            error.cause shouldEqual input
        }
    }

}
