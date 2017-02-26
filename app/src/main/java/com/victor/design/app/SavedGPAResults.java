package com.victor.design.app;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SavedGPAResults extends ActionBarActivity {

    private LinearLayout gpaLayout;
    private TextView showResults;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.saved_gps);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setTitle("Saved Results");
        setSupportActionBar(toolbar);

        gpaLayout = (LinearLayout) findViewById(R.id.llGPAContainer);
        showResults = (TextView) findViewById(R.id.tvShowResult);

    }

    private class BackgroundLoad extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
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

        switch (id) {
            case R.id.menuAbout:
                Intent about = new Intent(getApplicationContext(), AboutPage.class);
                startActivity(about);
                break;
            case R.id.menuCalculateGPA:
                Intent gpa = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(gpa);
                break;
            case R.id.menuCalculateCGPA:
                Intent cgpa = new Intent(getApplicationContext(), CGPAPrompt.class);
                startActivity(cgpa);
                break;
            case R.id.menuSavedGPs:
                Intent savedgps = new Intent(getApplicationContext(), SavedGPs.class);
                startActivity(savedgps);
                break;
        }

        return super.onOptionsItemSelected(item);
    }



}
