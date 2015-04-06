package com.example.ucucair2.janken;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends ActionBarActivity {
    ImageView cpu;
    ImageView player;
    TextView result;
    Random r = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cpu = (ImageView)findViewById(R.id.cpu);
        player = (ImageView)findViewById(R.id.player);
        result = (TextView)findViewById(R.id.result);
    }

    public void goo(View v) {
        player.setImageResource(R.drawable.goo);
        int random = r.nextInt();
        if (random == 0) {
            cpu.setImageResource(R.drawable.goo);
            result.setText("引き分けです！");
        } else if (random == 1) {
            cpu.setImageResource(R.drawable.choki);
            result.setText("あなたの勝ちです！");
        } else {
            cpu.setImageResource(R.drawable.paa);
            result.setText("あなたの負けです！");
        }
    }
    public void choki(View v) {
        player.setImageResource(R.drawable.choki);
        int random = r.nextInt();
        if (random == 0) {
            cpu.setImageResource(R.drawable.goo);
            result.setText("あなたの負けです！");
        } else if (random == 1) {
            cpu.setImageResource(R.drawable.choki);
            result.setText("引き分けです！");
        } else {
            cpu.setImageResource(R.drawable.paa);
            result.setText("あなたの勝ちです！");
        }
    }
    public void paa(View v) {
        player.setImageResource(R.drawable.paa);
        int random = r.nextInt();
        if (random == 0) {
            cpu.setImageResource(R.drawable.goo);
            result.setText("あなたの勝ちです！");
        } else if (random == 1) {
            cpu.setImageResource(R.drawable.choki);
            result.setText("あなたの負けです！");
        } else {
            cpu.setImageResource(R.drawable.paa);
            result.setText("引き分けです！");
        }
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
