package com.example.ucucair2.tapamole;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends ActionBarActivity {
    TextView scoreText;
    TextView timeText;

    int[] imageResources = {
            R.id.imageButton1, R.id.imageButton2, R.id.imageButton3,
            R.id.imageButton4, R.id.imageButton5, R.id.imageButton6,
            R.id.imageButton7, R.id.imageButton8, R.id.imageButton9,
            R.id.imageButton10, R.id.imageButton11, R.id.imageButton12
    };

    Mole[] moles;

    int time, score;

    Timer timer;
    TimerTask timerTask;
    Handler h;
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreText = (TextView)findViewById(R.id.scoreText);
        timeText = (TextView)findViewById(R.id.timeText);

        moles = new Mole[12];
        for (int i=0; i<12; i++) {
            ImageView imageView = (ImageView)findViewById(imageResources[i]);
            moles[i] = new Mole(imageView);
        }
        h = new Handler();
    }

    public void start(View v) {
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                h.post(new Runnable() {
                    @Override
                    public void run() {
                        int r = random.nextInt(12);
                        moles[r].start();

                        time = time -1;
                        timeText.setText(String.valueOf(time));
                        if (time <= 0) {
                            timer.cancel();
                        }
                    }
                });
            }
        };

        time = 60;
        score = 0;
        timeText.setText(String.valueOf(time));
        scoreText.setText(String.valueOf(score));
        timer.schedule(timerTask, 0, 1000);
    }

    public void tapMole(View v){
        String tag_str = (String) v.getTag();
        int tag_int = Integer.parseInt(tag_str.replaceAll("[^0-9]",""));
        score += moles[tag_int-1].tapMole();
        scoreText.setText(String.valueOf(score));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
