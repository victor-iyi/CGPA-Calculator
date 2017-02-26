package com.victor.design.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements OnClickListener {

    private static String feedback = "0";
    private TextView result, prompt;
    private ImageButton back;
    private StringBuilder string = new StringBuilder();
    private Button zero, one, two, three, four, five, six, seven, eight, nine, submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle("GPA Calculator");
        setSupportActionBar(toolbar);
        initializeVars();
        prompt.setText("Enter the number of courses offered then press Go.");
    }

    private void initializeVars() {
        zero = (Button) findViewById(R.id.b0);
        one = (Button) findViewById(R.id.b1);
        two = (Button) findViewById(R.id.b2);
        three = (Button) findViewById(R.id.b3);
        four = (Button) findViewById(R.id.b4);
        five = (Button) findViewById(R.id.b5);
        six = (Button) findViewById(R.id.b6);
        seven = (Button) findViewById(R.id.b7);
        eight = (Button) findViewById(R.id.b8);
        nine = (Button) findViewById(R.id.b9);
        submit = (Button) findViewById(R.id.bSubmit);
        result = (TextView) findViewById(R.id.tvResult);
        prompt = (TextView) findViewById(R.id.tvPrompt);
        back = (ImageButton) findViewById(R.id.ibBack);
        setOnClicks();
    }

    private void setOnClicks() {
        zero.setOnClickListener(this);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        back.setOnClickListener(this);
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.b0:
                appendValue(zero.getText().toString());
                break;
            case R.id.b1:
                appendValue(one.getText().toString());
                break;
            case R.id.b2:
                appendValue(two.getText().toString());
                break;
            case R.id.b3:
                appendValue(three.getText().toString());
                break;
            case R.id.b4:
                appendValue(four.getText().toString());
                break;
            case R.id.b5:
                appendValue(five.getText().toString());
                break;
            case R.id.b6:
                appendValue(six.getText().toString());
                break;
            case R.id.b7:
                appendValue(seven.getText().toString());
                break;
            case R.id.b8:
                appendValue(eight.getText().toString());
                break;
            case R.id.b9:
                appendValue(nine.getText().toString());
                break;
            case R.id.ibBack:
                popValue();
                break;
            case R.id.bSubmit:
                int value;
                value = Integer.parseInt(feedback);
                if (value <= 20 && value >= 1) {
                    Bundle basket = new Bundle();
                    basket.putInt("noOfCourses", value);
                    Intent intent = new Intent(MainActivity.this, CalculateGPA.class);
                    intent.putExtras(basket);
                    startActivity(intent);
                } else
                    Message.show(MainActivity.this, "Maximum number of courses: 20\n" +
                            "Minimum number of course: 1");
                break;
        }
    }

    private void appendValue(String whatToAppend) {
        if (!(feedback.length() > 10))
            string.append(whatToAppend);
        feedback = string.toString();
        result.setText(feedback);

    }

    private void popValue() {
        if (feedback.length() < 1) {
            feedback = "0";
            Message.show(MainActivity.this, "Cannot go any further!");
        } else {
            string.delete(feedback.length() - 1, feedback.length());
            feedback = string.toString();
            result.setText(feedback);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        string = new StringBuilder();
        feedback = "0";
        result.setText(feedback);
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

        switch(id) {
            case R.id.menuAbout:
                Intent about = new Intent(getApplicationContext(), AboutPage.class);
                startActivity(about);
                break;
            case R.id.menuCalculateGPA:
                break;
            case R.id.menuCalculateCGPA:
                Intent cgpa = new Intent(getApplicationContext(), CGPAPrompt.class);
                startActivity(cgpa);
                break;
            case R.id.menuSavedGPs:
                Intent savedGPs = new Intent(getApplicationContext(), SavedGPs.class);
                startActivity(savedGPs);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
