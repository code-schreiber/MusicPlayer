package com.toolslab.musicplayer.view.artists

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.toolslab.musicplayer.R
import com.toolslab.musicplayer.base_repository.model.ArtistViewModel
import com.toolslab.musicplayer.view.artist.ArtistActivity
import com.toolslab.musicplayer.view.base.BaseActivity
import javax.inject.Inject

class ArtistsActivity : BaseActivity(), ArtistsContract.View, ArtistsAdapter.OnArtistClickListener {

    @BindView(R.id.activity_artists_recycler_view)
    lateinit var recyclerView: RecyclerView

    @Inject
    internal lateinit var presenter: ArtistsContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artists)
        setTitle(R.string.top_artists)
        ButterKnife.bind(this)
        presenter.bind(this)
    }

    override fun onDestroy() {
        presenter.unbind(this)
        super.onDestroy()
    }

    override fun setViewModels(viewModels: List<ArtistViewModel>) {
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ArtistsAdapter(viewModels, this)
    }

    override fun startArtistActivity(artistName: String) {
        ArtistActivity.start(this, artistName)
    }

    override fun onArtistClicked(position: Int) {
        presenter.onArtistClicked(position)
    }

}
