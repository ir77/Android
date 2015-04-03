package com.example.ucucair2.clap;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;


public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener {
    ImageButton button;
    Clap clap;
    Spinner spinner;
    int repeat = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (ImageButton)findViewById(R.id.imgBtn);
        spinner = (Spinner)findViewById(R.id.spinner);

        clap = new Clap(this.getApplicationContext());
        clap.play();

        String[] strArray = new String[5];
        strArray[0] = "パンッ！";
        strArray[1] = "パンッパンッ！！";
        strArray[2] = "パンッパンッパンッ！！";
        strArray[3] = "4回";
        strArray[4] = "5回";

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
            this, android.R.layout.simple_spinner_item, strArray
        );
        arrayAdapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        );
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(this);
    }

    public void onButtonPushed(View v) {
        Log.v("tag", "message message");
        clap.repeatPlay(repeat);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int pos, long id){
        repeat = pos+1;
        Log.v("tag", "message");
        clap.play();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
