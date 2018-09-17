package com.toolslab.musicplayer.view.artists

import com.nhaarman.mockito_kotlin.*
import com.toolslab.musicplayer.base_repository.exception.NoConnectionException
import com.toolslab.musicplayer.base_repository.model.ArtistViewModel
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import java.util.concurrent.TimeUnit


class ArtistsPresenterTest {

    private val mockViewModels: List<ArtistViewModel> = mock()
    private val mockView: ArtistsContract.View = mock()

    private val underTest = ArtistsPresenter()

    companion object {

        @BeforeClass
        @JvmStatic
        fun setUpRxSchedulers() {
            val immediate = object : Scheduler() {

                override fun scheduleDirect(run: Runnable, delay: Long, unit: TimeUnit) =
                        super.scheduleDirect(run, 0, unit)

                override fun createWorker() =
                        ExecutorScheduler.ExecutorWorker { it.run() }
            }

            RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediate }
            RxJavaPlugins.setIoSchedulerHandler { immediate }
        }
    }

    @Before
    fun setUp() {
        underTest.compositeDisposable = mock()
        underTest.artistsInteractor = mock()
        underTest.backgroundMusicPlayer = mock()
    }

    @Test
    fun onBound() {
        whenever(underTest.artistsInteractor.listArtists()).thenReturn(Single.just(mockViewModels))

        underTest.bind(mockView)

        underTest.artists shouldEqual mockViewModels
        verify(mockView).setViewModels(mockViewModels)
        verify(underTest.compositeDisposable).add(any())
        verifyZeroInteractions(underTest.backgroundMusicPlayer)
    }

    @Test
    fun onBoundWithNoConnectionException() {
        whenever(underTest.artistsInteractor.listArtists()).thenReturn(Single.error(NoConnectionException(Exception())))

        underTest.bind(mockView)

        verify(mockView).showNoConnectionError()
        verify(underTest.compositeDisposable).add(any())
        verifyNoMoreInteractions(mockView)
        verifyZeroInteractions(underTest.backgroundMusicPlayer)
    }

    @Test
    fun onBoundWithDefaultException() {
        whenever(underTest.artistsInteractor.listArtists()).thenReturn(Single.error(Exception()))

        underTest.bind(mockView)

        verify(mockView).showDefaultError()
        verify(underTest.compositeDisposable).add(any())
        verifyNoMoreInteractions(mockView)
        verifyZeroInteractions(underTest.backgroundMusicPlayer)
    }

    @Test
    fun onUnbound() {
        underTest.unbind(mockView)

        verify(underTest.compositeDisposable).clear()
        verify(underTest.backgroundMusicPlayer).releaseTrack()
        verifyZeroInteractions(underTest.artistsInteractor)
        verifyZeroInteractions(mockView)
    }

}
