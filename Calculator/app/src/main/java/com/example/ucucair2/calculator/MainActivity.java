package com.example.ucucair2.calculator;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import static android.util.Log.*;

public class MainActivity extends ActionBarActivity {
    int number1;
    int number2;
    int answer;
    int ope;

    TextView num1Text;
    TextView opeText;
    TextView num2Text;
    TextView answerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initCalc();
    }

    public void initCalc() {
        number1 = 0;
        number2 = 0;
        answer = 0;
        ope = 0;

        num1Text = (TextView)findViewById(R.id.num1);
        num1Text.setText("0");
        opeText = (TextView)findViewById(R.id.ope);
        opeText.setText("?");
        num2Text = (TextView)findViewById(R.id.num2);
        num2Text.setText("0");
        answerText = (TextView)findViewById(R.id.answer);
        answerText.setText("");
    }

    public void btNumber(View v) {
        if (ope == -1) {
            initCalc();
        }
        if (ope == 0) {
            number1 = number1 * 10 + Integer.parseInt((String) v.getTag());
            num1Text.setText(number1 + "");
        } else {
            number2 = number2 * 10 + Integer.parseInt((String) v.getTag());
            num2Text.setText(number2 + "");
        }
    }

    public void btEqual(View v) {
        if (ope == 1) {
            answer = number1 + number2;
            answerText.setText("="+answer);
            ope = -1;
        } else if (ope == 2) {
            answer = number1 - number2;
            answerText.setText("="+answer);
            ope = -1;
        } else if (ope == 3) {
            answer = number1 * number2;
            answerText.setText("="+answer);
            ope = -1;
        } else if (ope == 4) {
            answer = number1 / number2;
            answerText.setText("=" + answer);
            ope = -1;
        }
    }
    public void btOpe(View v) {
        if (v.getTag().equals("1")) {
            ope = 1;
            opeText.setText("+");
        } else if (v.getTag().equals("2")) {
            ope = 2;
            opeText.setText("-");
        } else if (v.getTag().equals("3")) {
            ope = 3;
            opeText.setText("×");
        } else if (v.getTag().equals("4")) {
            ope = 4;
            opeText.setText("÷");
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
