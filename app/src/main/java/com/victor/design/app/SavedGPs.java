package com.victor.design.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import android.widget.TabHost.TabSpec;

public class SavedGPs extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.save_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setTitle("Saved Grade Points");
        setSupportActionBar(toolbar);

        /* Initializes the TabHost Layout */
        TabHost tabHost = (TabHost) findViewById(R.id.tabhost);
        tabHost.setup();

        //GPA Layout
        TabSpec gpaLayout = tabHost.newTabSpec("GPA Layout");
        gpaLayout.setContent(R.id.tabSaveGPAList);
        gpaLayout.setIndicator("Saved GPA");
        tabHost.addTab(gpaLayout);

        //CGPA Layout
        TabSpec cgpaLayout = tabHost.newTabSpec("CGPA Layout");
        cgpaLayout.setContent(R.id.tabSaveCGPAList);
        cgpaLayout.setIndicator("Saved CGPA");
        tabHost.addTab(cgpaLayout);


        // Set ListAdapter for GPA
        String[] gpResults = {"Result 1", "Result 2", "Result 3"}; // dynamically load from text file or db

        if (gpResults.length == 0) {
            TextView gpaEmptyText = (TextView) findViewById(R.id.tvEmptyGPA);
            gpaEmptyText.setVisibility(TextView.VISIBLE);
            ListView gpaListView = (ListView) findViewById(R.id.lvListGPA);
            gpaListView.setVisibility(ListView.GONE);
        } else {
            ListAdapter gpaListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, gpResults);
            ListView gpaListView = (ListView) findViewById(R.id.lvListGPA);
            gpaListView.setAdapter(gpaListAdapter);

            gpaListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String gpaResult = String.valueOf(adapterView.getItemAtPosition(i));
                    Toast.makeText(SavedGPs.this, gpaResult, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SavedGPs.this, SavedGPAResults.class);
                    startActivity(intent);
                }
            });
        }


        // Set ListAdapter for CGPA
        String[] cgpaResults = {"Result 1", "Result 2", "Result 3"}; // dynamically load from text file or db
        if (cgpaResults.length == 0) {
            TextView cgpaEmptyText = (TextView) findViewById(R.id.tvEmptyCGPA);
            cgpaEmptyText.setVisibility(TextView.VISIBLE);
            ListView cgpaListView = (ListView) findViewById(R.id.lvListCGPA);
            cgpaListView.setVisibility(ListView.GONE);
        } else {
            ListAdapter cgpaListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cgpaResults);
            ListView cgpaListView = (ListView) findViewById(R.id.lvListCGPA);
            cgpaListView.setAdapter(cgpaListAdapter);

            cgpaListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String cgpaResult = String.valueOf(adapterView.getItemAtPosition(i));
                    Toast.makeText(SavedGPs.this, cgpaResult, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SavedGPs.this, SavedCGPAResults.class);
                    startActivity(intent);
                }
            });
        }

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
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}