package com.example.ucucair2.tapamole;

import android.os.Handler;
import android.widget.ImageView;

/**
 * Created by ucucAir2 on 15/04/09.
 */
public class Mole {
    int state;  // もぐらの状態 0:潜っている 1:出てきている 2:叩かれている
    ImageView moleImage;

    Handler h;
    Runnable hide;

    public Mole(ImageView imageView) {
        state = 0;
        moleImage = imageView;
        moleImage.setImageResource(R.drawable.mole1);

        h = new Handler();
        hide = new Runnable() {
            @Override
            public void run() {
                state = 0;
                moleImage.setImageResource(R.drawable.mole1);
            }
        };
    }

    public void start() {
        if (state == 0) {
            state = 1;
            moleImage.setImageResource(R.drawable.mole2);
            h.postDelayed(hide, 1000);
        }
    }

    public int tapMole(){
        if (state == 1) {
            state = 2;
            moleImage.setImageResource(R.drawable.mole3);

            h.removeCallbacks(hide);
            h.postDelayed(hide, 1000);
            return 1;
        }
        return 0;
    }
}
