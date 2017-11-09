package com.yuneec.videostreaming;

import android.view.Surface;

import java.io.IOException;

/**
 * Created by sushma on 10/13/17.
 *
 * Abstract class to manage streaming of video
 */

public abstract class VideoPlayer {
    abstract void initializePlayer();
    abstract void setSurface(Surface surface) throws VideoPlayerException;
    abstract void setDataSource(String url) throws IOException, VideoPlayerException;;
    abstract void start() throws VideoPlayerException;
    abstract void stop() throws VideoPlayerException;
    abstract void releasePlayer() throws VideoPlayerException;
    abstract boolean isPlaying() throws VideoPlayerException;

    public enum PlayerType {
        LIVE_STREAM;
    }

    public static VideoPlayer getPlayer(PlayerType type) {
        switch (type) {
            case LIVE_STREAM:
                return new RTSPPlayer();
            default:
                throw new IllegalArgumentException("Unknown VideoPlayer :" + type);
        }
    }
}
