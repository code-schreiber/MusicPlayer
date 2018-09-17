package com.toolslab.musicplayer.base_mvp

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.spy
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyZeroInteractions
import org.amshove.kluent.shouldEqual
import org.junit.Test

class BasePresenterTest {

    private val mockView: BaseView = mock()

    private val underTest = BasePresenterSubclass()

    @Test
    fun bind() {
        val spyOfUnderTest = spy(underTest)

        spyOfUnderTest.bind(mockView)

        spyOfUnderTest.getProtectedView() shouldEqual mockView
        verify(spyOfUnderTest).onBound(mockView)
    }

    @Test
    fun onBound() {
        underTest.onBound(mockView)

        verifyZeroInteractions(mockView)
    }

    @Test
    fun unbind() {
        val spyOfUnderTest = spy(underTest)

        spyOfUnderTest.unbind(mockView)

        verify(spyOfUnderTest).onUnbound(mockView)
    }

    @Test
    fun onUnbound() {
        underTest.onUnbound(mockView)

        verifyZeroInteractions(mockView)
    }

    open class BasePresenterSubclass : BasePresenter<BaseView>(), MvpPresenter<BaseView> {
        fun getProtectedView() = view
    }

}
