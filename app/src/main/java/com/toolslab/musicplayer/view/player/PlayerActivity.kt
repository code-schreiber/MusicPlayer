package com.toolslab.musicplayer.view.player

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.toolslab.musicplayer.R
import com.toolslab.musicplayer.view.base.BaseActivity
import javax.inject.Inject

class PlayerActivity : BaseActivity(), PlayerContract.View {

    @BindView(R.id.activity_player_play_pause_button)
    internal lateinit var playPauseButton: ImageButton

    @Inject
    internal lateinit var presenter: PlayerContract.Presenter

    internal companion object {
        private const val TRACK_TITLE = "TRACK_TITLE"

        internal fun start(context: Context?, trackTitle: String) {
            val intent = Intent(context, PlayerActivity::class.java)
            intent.putExtra(TRACK_TITLE, trackTitle)
            context?.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        ButterKnife.bind(this)
        presenter.bind(this)
        intent.extras?.getString(TRACK_TITLE)?.let { presenter.onTrackTitleKnown(it) }
    }

    override fun onDestroy() {
        presenter.unbind(this)
        super.onDestroy()
    }

    override fun setTitle(title: String) {
        this.title = title
    }

    override fun setPlayPauseButtonToPlaying() {
        playPauseButton.setBackgroundResource(R.drawable.ic_pause_circle_outline_black_24dp)
    }

    override fun setPlayPauseButtonToPaused() {
        playPauseButton.setBackgroundResource(R.drawable.ic_play_circle_outline_black_24dp)
    }

    @OnClick(R.id.activity_player_play_pause_button)
    fun onPlayPauseClicked() {
        presenter.onPlayPauseClicked()
    }

}
