package com.fashionone.rahatlaticisesler.Components;

import android.media.MediaPlayer;

public class CustomMediaPlayer extends MediaPlayer {

    private int volume;
    @Override
    public void setVolume(float leftVolume, float rightVolume) {
        super.setVolume(leftVolume, rightVolume);
        volume = (int)(leftVolume * 100);
    }

    public int getVolume(){
        return volume;
    }
}
