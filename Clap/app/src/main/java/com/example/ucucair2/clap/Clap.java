package com.example.ucucair2.clap;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

/**
 * Created by ucucAir2 on 15/04/02.
 */
public class Clap {
    private SoundPool soundPool;
    private int soundId;

    public Clap(Context context) {
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundId = soundPool.load(context, R.raw.clap, 1);
    }

    public void play() {
        soundPool.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void repeatPlay(int repeat) {
        int count = 0;
        while (count < repeat) {
            play();
            count++;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
