package com.toolslab.musicplayer.base_repository.converter

import com.toolslab.musicplayer.base_network.model.User
import org.amshove.kluent.shouldEqual
import org.junit.Test

class ArtistViewModelConverterTest {

    private val username = "username"
    private val avatarUrl = "avatarUrl"
    private val user = User("", "", username, "", "", avatarUrl)

    private val underTest = ArtistViewModelConverter()

    @Test
    fun convert() {
        val itemViewModel = underTest.convert(user)

        itemViewModel.name shouldEqual username
        itemViewModel.thumbnailImageUrl shouldEqual avatarUrl
    }

}
