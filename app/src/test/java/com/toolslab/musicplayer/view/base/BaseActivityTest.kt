package com.toolslab.musicplayer.view.base

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.toolslab.musicplayer.R
import org.junit.Before
import org.junit.Test

class BaseActivityTest {

    private val underTest = BaseActivity()

    @Before
    fun setUp() {
        underTest.uiMessenger = mock()
    }

    @Test
    fun showNoConnectionError() {
        underTest.showNoConnectionError()

        verify(underTest.uiMessenger).showMessage(underTest, R.string.error_no_connection)
    }

    @Test
    fun showDefaultError() {
        underTest.showDefaultError()

        verify(underTest.uiMessenger).showMessage(underTest, R.string.error_default)
    }

}
