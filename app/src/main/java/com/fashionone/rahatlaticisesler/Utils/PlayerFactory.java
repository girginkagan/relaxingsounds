package com.fashionone.rahatlaticisesler.Utils;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import com.fashionone.rahatlaticisesler.Components.CustomMediaPlayer;

import java.io.IOException;
import java.util.ArrayList;

public class PlayerFactory {
    public static ArrayList<CustomMediaPlayer> player = new ArrayList<>();
    public static ArrayList<String> songList = new ArrayList<>();

    public static void setVolume(float vol, String playerTag){
        if(songList.contains(playerTag)) {
            int playerOrder = songList.indexOf(playerTag);
            player.get(playerOrder).setVolume(vol, vol);
        }
    }

    public static void playSong(Context context, String song, float volume){
        if(!songList.contains(song)) {
            CustomMediaPlayer pl = new CustomMediaPlayer();
            pl.setLooping(true);
            pl.setVolume(volume, volume);
            player.add(pl);
            songList.add(song);
            try {
                AssetFileDescriptor descriptor = context.getAssets().openFd(song);
                player.get(player.size() - 1).setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
                player.get(player.size() - 1).setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.start();
                    }
                });
                player.get(player.size() - 1).prepareAsync();
            } catch (IOException e) {

            }
        }
        else{
            player.get(songList.indexOf(song)).start();
        }
    }

    public static void pauseSong(String song){
        if(songList.contains(song)){
            player.get(songList.indexOf(song)).pause();
        }
    }

    public static boolean checkIfSongPlaying(String song) {
        return songList.contains(song) && player.get(songList.indexOf(song)).isPlaying();
    }

    public static int getMediaPlayerVolume(String song) {
        if(songList.contains(song)){
            return player.get(songList.indexOf(song)).getVolume();
        }

        return 100;
    }
}
