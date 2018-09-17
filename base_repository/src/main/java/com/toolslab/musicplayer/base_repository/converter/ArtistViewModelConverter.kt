package com.toolslab.musicplayer.base_repository.converter

import com.toolslab.musicplayer.base_network.model.User
import com.toolslab.musicplayer.base_repository.model.ArtistViewModel
import javax.inject.Inject

class ArtistViewModelConverter @Inject constructor() : Converter<User, ArtistViewModel> {

    override fun convert(source: User) =
            ArtistViewModel(source.username, source.avatarUrl)

}
