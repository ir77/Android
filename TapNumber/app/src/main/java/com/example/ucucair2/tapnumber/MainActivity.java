package com.example.ucucair2.tapnumber;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class MainActivity extends Activity {

    int[] array;
    String question;
    int correctCounter;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.textView);
        start();
    }

    public void start() {
        array = new int[4];
        Random rand = new Random();
        array[0] = rand.nextInt(4)+1;
        array[1] = rand.nextInt(4)+1;
        array[2] = rand.nextInt(4)+1;
        array[3] = rand.nextInt(4)+1;

        question = Integer.toString(array[0]) + Integer.toString(array[1]) + Integer.toString(array[2]) + Integer.toString(array[3]);
        textView.setText(question);
        correctCounter = 0;
    }

    public void endCheck () {
        if (correctCounter == 4) {
            start();
        }
    }

    public void number1 (View v) {
        if (array[correctCounter] == 1) {
            question = question.substring(1);
            textView.setText(question);
            correctCounter++;
            endCheck();
        } else {
            Toast.makeText(this, "数字が違うよ", Toast.LENGTH_SHORT).show();
        }
    }
    public void number2 (View v) {
        if (array[correctCounter] == 2) {
            question = question.substring(1);
            textView.setText(question);
            correctCounter++;
            endCheck();
        } else {
            Toast.makeText(this, "数字が違うよ", Toast.LENGTH_SHORT).show();
        }
    }
    public void number3 (View v) {
        if (array[correctCounter] == 3) {
            question = question.substring(1);
            textView.setText(question);
            correctCounter++;
            endCheck();
        } else {
            Toast.makeText(this, "数字が違うよ", Toast.LENGTH_SHORT).show();
        }
    }
    public void number4 (View v) {
        if (array[correctCounter] == 4) {
            question = question.substring(1);
            textView.setText(question);
            correctCounter++;
            endCheck();
        } else {
            Toast.makeText(this, "数字が違うよ", Toast.LENGTH_SHORT).show();
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
