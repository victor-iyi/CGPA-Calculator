package com.victor.design.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class AboutPage extends  ActionBarActivity {

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.about_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle("About");
        setSupportActionBar(toolbar);
    }


    @Override
    protected void onPause() {
        super.onPause();
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
                Intent savedGPs = new Intent(getApplicationContext(), SavedGPs.class);
                startActivity(savedGPs);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
