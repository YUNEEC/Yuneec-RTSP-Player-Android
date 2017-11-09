package com.yuneec.videostreaming;

import android.util.Log;
import android.view.Surface;

import java.io.IOException;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * Created by sushma on 10/13/17.
 */

public class RTSPPlayer extends VideoPlayer {

    private IMediaPlayer mMediaPlayer;

    private static final String TAG = "RTSPPlayer";

    private String PLAYER_NOT_INITALIZED = "Player null - Initialize the player first";

    private String NULL_URL = "Data Source is null";

    @Override
    public void initializePlayer() {
        this.mMediaPlayer = createPlayer();
        this.mMediaPlayer.setScreenOnWhilePlaying(true);
    }

    @Override
    public void setSurface(Surface surface) throws VideoPlayerException {
        if (mMediaPlayer == null) {
            throw new VideoPlayerException(PLAYER_NOT_INITALIZED);
        } else {
            this.mMediaPlayer.setSurface(surface);
        }
    }

    @Override
    public void setDataSource(String url) throws IOException, VideoPlayerException {
        if (mMediaPlayer == null) {
            throw new VideoPlayerException(PLAYER_NOT_INITALIZED);
        } else if (url == null) {
            throw new VideoPlayerException(NULL_URL);
        } else {
            mMediaPlayer.setDataSource(url);
            mMediaPlayer.prepareAsync();
        }
    }

    @Override
    public void start() throws VideoPlayerException {
        if (mMediaPlayer == null) {
            throw new VideoPlayerException(PLAYER_NOT_INITALIZED);
        } else if (mMediaPlayer.isPlaying()) {
            Log.d(TAG, "Video is already playing");
        } else {
            mMediaPlayer.start();
        }
    }

    @Override
    public void stop() throws VideoPlayerException {
        if (mMediaPlayer == null) {
            throw new VideoPlayerException(PLAYER_NOT_INITALIZED);
        } else {
            mMediaPlayer.stop();
        }
    }

    @Override
    public void releasePlayer() throws VideoPlayerException {
        if (mMediaPlayer == null) {
            throw new VideoPlayerException(PLAYER_NOT_INITALIZED);
        } else {
            mMediaPlayer.release();
        }
    }

    @Override
    public boolean isPlaying() throws VideoPlayerException {
        if (mMediaPlayer == null) {
            throw new VideoPlayerException(PLAYER_NOT_INITALIZED);
        } else {
            return mMediaPlayer.isPlaying();
        }
    }

    private IMediaPlayer createPlayer() {
        IMediaPlayer mediaPlayer = null;
        IjkMediaPlayer ijkMediaPlayer = null;
        ijkMediaPlayer = new IjkMediaPlayer();
        ijkMediaPlayer.native_setLogLevel(IjkMediaPlayer.IJK_LOG_DEBUG);
        // set options to optimize streaming
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "infbuf", 0);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "fflags", "nobuffer");
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "packet-buffering", 0);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "islive",
                                 1);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "max_delay", 0);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "reorder_queue_size", 0);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "rtsp_transport", "udp");
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "framedrop", 1);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "start-on-prepared", 1);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_CODEC, "skip_frame", 0);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_CODEC, "skip_loop_filter", 0);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "flush_packets", 1);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "sync", "ext");
        mediaPlayer = ijkMediaPlayer;
        return mediaPlayer;
    }
}
