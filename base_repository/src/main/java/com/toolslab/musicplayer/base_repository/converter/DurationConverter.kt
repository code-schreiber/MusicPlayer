package com.toolslab.musicplayer.base_repository.converter

import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class DurationConverter @Inject constructor() : Converter<String, String> {

    override fun convert(source: String): String {
        val millis = source.toLong() * 1000L
        val dateFormat = SimpleDateFormat("mm:ss", Locale.getDefault())
        return dateFormat.format(Date(millis))
    }

}
