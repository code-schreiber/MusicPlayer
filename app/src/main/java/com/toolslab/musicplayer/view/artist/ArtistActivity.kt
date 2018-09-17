package com.toolslab.musicplayer.view.artist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.toolslab.musicplayer.R
import com.toolslab.musicplayer.base_repository.model.TrackViewModel
import com.toolslab.musicplayer.view.base.BaseActivity
import com.toolslab.musicplayer.view.player.PlayerActivity
import javax.inject.Inject

class ArtistActivity : BaseActivity(), ArtistContract.View, ArtistAdapter.OnSongClickListener {

    @BindView(R.id.activity_artist_recycler_view)
    lateinit var recyclerView: RecyclerView

    @Inject
    internal lateinit var presenter: ArtistContract.Presenter

    internal companion object {
        private const val ARTIST_NAME = "ARTIST_NAME"

        internal fun start(context: Context?, artistName: String) {
            val intent = Intent(context, ArtistActivity::class.java)
            intent.putExtra(ARTIST_NAME, artistName)
            context?.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist)
        ButterKnife.bind(this)
        presenter.bind(this)
        intent.extras?.getString(ARTIST_NAME)?.let { presenter.onArtistNameKnown(it) }
    }

    override fun onDestroy() {
        presenter.unbind(this)
        super.onDestroy()
    }

    override fun setArtistName(artistName: String) {
        title = artistName
    }

    override fun setViewModels(viewModels: List<TrackViewModel>) {
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ArtistAdapter(viewModels, this)
    }

    override fun startPlayerActivity(trackTitle: String) {
        PlayerActivity.start(this, trackTitle)
    }

    override fun onSongClicked(position: Int) {
        presenter.onSongClicked(position)
    }

}
