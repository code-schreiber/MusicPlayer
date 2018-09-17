package com.toolslab.musicplayer.view.base

import android.content.Context
import android.support.annotation.StringRes
import android.widget.Toast
import javax.inject.Inject

internal class UiMessenger @Inject constructor() {

    internal fun showMessage(context: Context, @StringRes id: Int) {
        Toast.makeText(context, id, Toast.LENGTH_SHORT).show()
    }

}
