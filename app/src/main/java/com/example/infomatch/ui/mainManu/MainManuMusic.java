package com.example.infomatch.ui.mainManu;

import android.media.MediaPlayer;

public class MainManuMusic implements Runnable{
    private MediaPlayer mediaPlayer;

    private boolean playMusic;

    public MainManuMusic(MediaPlayer mediaPlayer){
        this.mediaPlayer = mediaPlayer;
        this.playMusic=true;
    }
    public void stopMusic(){
        this.playMusic = false;
    }

    public void startMusic(){
        this.playMusic = true;
    }

    @Override
    public void run() {
        while (playMusic){
                if (!this.mediaPlayer.isPlaying()) {
                    this.mediaPlayer.start();
                }
        }
        this.mediaPlayer.stop();
    }
}
