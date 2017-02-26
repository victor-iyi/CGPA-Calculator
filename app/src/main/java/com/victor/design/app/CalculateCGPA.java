package com.victor.design.app;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.InputMismatchException;


public class CalculateCGPA extends ActionBarActivity implements View.OnClickListener {

    private int noOfSemesters;
    private LinearLayout[] levels, semesters;
    private EditText[] gpas;
    private Button calculate, reset;
    private String saveText, saveResult;
    protected String[] saveGrade;
    protected int[] levelSemester = new int[2]; // level = index[0], semester = index[1]

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.calculatecgpa);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle("CGPA Calculator");
        setSupportActionBar(toolbar);

        Bundle gotBasket = getIntent().getExtras();
        noOfSemesters = gotBasket.getInt("noOfSemesters");
        saveGrade = new String[noOfSemesters];
        initializeVars();

        showLayout();

    }

    private void initializeVars() {

        levels = new LinearLayout[]{
                (LinearLayout) findViewById(R.id.ll100L), (LinearLayout) findViewById(R.id.ll200L),
                (LinearLayout) findViewById(R.id.ll300L), (LinearLayout) findViewById(R.id.ll400L),
                (LinearLayout) findViewById(R.id.ll500L), (LinearLayout) findViewById(R.id.ll600L),
                (LinearLayout) findViewById(R.id.ll700L)
        };

        semesters = new LinearLayout[]{
                (LinearLayout) findViewById(R.id.ll100L1), (LinearLayout) findViewById(R.id.ll100L2),
                (LinearLayout) findViewById(R.id.ll200L1), (LinearLayout) findViewById(R.id.ll200L2),
                (LinearLayout) findViewById(R.id.ll300L1), (LinearLayout) findViewById(R.id.ll300L2),
                (LinearLayout) findViewById(R.id.ll400L1), (LinearLayout) findViewById(R.id.ll400L2),
                (LinearLayout) findViewById(R.id.ll500L1), (LinearLayout) findViewById(R.id.ll500L2),
                (LinearLayout) findViewById(R.id.ll600L1), (LinearLayout) findViewById(R.id.ll600L2),
                (LinearLayout) findViewById(R.id.ll700L1), (LinearLayout) findViewById(R.id.ll700L2)
        };

        gpas = new EditText[]{
                (EditText) findViewById(R.id.et100L1), (EditText) findViewById(R.id.et100L2),
                (EditText) findViewById(R.id.et200L1), (EditText) findViewById(R.id.et200L2),
                (EditText) findViewById(R.id.et300L1), (EditText) findViewById(R.id.et300L2),
                (EditText) findViewById(R.id.et400L1), (EditText) findViewById(R.id.et400L2),
                (EditText) findViewById(R.id.et500L1), (EditText) findViewById(R.id.et500L2),
                (EditText) findViewById(R.id.et600L1), (EditText) findViewById(R.id.et600L2),
                (EditText) findViewById(R.id.et700L1), (EditText) findViewById(R.id.et700L2)
        };

        reset = (Button) findViewById(R.id.bReset);
        calculate = (Button) findViewById(R.id.bCalculate);

        reset.setOnClickListener(this);
        calculate.setOnClickListener(this);
    }

    private void visibilityGone(LinearLayout layout) {
        if (layout.getVisibility() == LinearLayout.VISIBLE)
            layout.setVisibility(LinearLayout.GONE);
    }

    private void showLayout() {

        switch (noOfSemesters) {
            case 1: /* 100 1st Semester */
                visibilityGone(levels[1]);
                visibilityGone(semesters[1]);
                levelSemester[0] = 100;
                levelSemester[1] = 1;
            case 2: /* 100 2nd Semester */
                visibilityGone(levels[1]);
                visibilityGone(semesters[2]);
                levelSemester[0] = 100;
                levelSemester[1] = 2;
            case 3: /* 200 1st Semester */
                visibilityGone(levels[2]);
                visibilityGone(semesters[3]);
                levelSemester[0] = 200;
                levelSemester[1] = 1;
            case 4: /* 200 2nd Semester */
                visibilityGone(levels[2]);
                visibilityGone(semesters[4]);
                levelSemester[0] = 200;
                levelSemester[1] = 2;
            case 5: /* 300 1st Semester */
                visibilityGone(levels[3]);
                visibilityGone(semesters[5]);
                levelSemester[0] = 300;
                levelSemester[1] = 1;
            case 6: /* 300 2nd Semester */
                visibilityGone(levels[3]);
                visibilityGone(semesters[6]);
                levelSemester[0] = 300;
                levelSemester[1] = 2;
            case 7: /* 400 1st Semester */
                visibilityGone(levels[4]);
                visibilityGone(semesters[7]);
                levelSemester[0] = 400;
                levelSemester[1] = 1;
            case 8: /* 400 2nd Semester */
                visibilityGone(levels[4]);
                visibilityGone(semesters[8]);
                levelSemester[0] = 400;
                levelSemester[1] = 2;
            case 9: /* 500 1st Semester */
                visibilityGone(levels[5]);
                visibilityGone(semesters[9]);
                levelSemester[0] = 500;
                levelSemester[1] = 1;
            case 10: /* 500 2nd Semester */
                visibilityGone(levels[5]);
                visibilityGone(semesters[10]);
                levelSemester[0] = 500;
                levelSemester[1] = 2;
            case 11: /* 600 1st Semester */
                visibilityGone(levels[6]);
                visibilityGone(semesters[11]);
                levelSemester[0] = 600;
                levelSemester[1] = 1;
            case 12: /* 600 2nd Semester */
                visibilityGone(levels[6]);
                visibilityGone(semesters[12]);
                levelSemester[0] = 600;
                levelSemester[1] = 2;
            case 13: /* 700 1st Semester */
                visibilityGone(semesters[13]);
                levelSemester[0] = 700;
                levelSemester[1] = 1;
            case 14: /* 700 2nd Semester */
                levelSemester[0] = 700;
                levelSemester[1] = 2;


        }
    }

    /*
     * Save GPA method
     * */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void save() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Save GPA Result");
        final EditText saveEditText = new EditText(this);
        saveEditText.setHint("Save as...");
        saveEditText.setInputType(InputType.TYPE_CLASS_TEXT);
        saveEditText.setMaxLines(1);
        builder.setView(saveEditText);

        // Save button clicked
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                saveText = saveEditText.getText().toString();
                if (!saveText.isEmpty()) {
                    new LoadInfoInDB().execute(saveResult);
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Saved!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Cancel button clicked
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bCalculate:
                double total = 0, gpa = 0;

                // looping through all the semesters and getting each gpa score.
                for (int i = 0; i < noOfSemesters; i++) {
                    String sgpa = gpas[i].getText().toString();

                    String dHelp = (i + 1) + "th";
                    if (i == 0) dHelp = "1st";
                    if (i == 1) dHelp = "2nd";
                    if (i == 2) dHelp = "3rd";
                    String validGradeErrorMessage = "Error detected at the " + dHelp + " field.\nAn invalid grade was entered.";

                    // handler for empty input
                    if (sgpa.isEmpty()) {
                        Message.show(this, "The " + dHelp + " field is empty!");
                        return;
                    }

                    // handler for type mismatch i.e !input.isDouble :)
                    try {
                        gpa = Double.parseDouble(sgpa);

                        if (gpa > 5.00) {
                            Message.show(this, validGradeErrorMessage);
                            return;
                        }
                    } catch (InputMismatchException e) {
                        Message.show(this, validGradeErrorMessage);
                        return;
                    } catch (NumberFormatException e) {
                        Message.show(this, validGradeErrorMessage);
                        return;
                    }

                    this.saveGrade[i] = String.valueOf(gpa); // saving the grade points to be stored in db
                    total += gpa;
                }

                double cgpa = total / noOfSemesters;
                saveResult = String.valueOf(cgpa);

                // Message showing off the CGPA result and prompt to save or not.
                AlertDialog.Builder builder = new AlertDialog.Builder(CalculateCGPA.this);
                builder.setMessage("Your CGPA = " + cgpa);
                builder.setCancelable(false);
                // Save the CGPA
                builder.setPositiveButton("Save Result", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        save();
                        dialogInterface.cancel();
                    }
                });
                // don't save the CGPA
                builder.setNegativeButton("Don't Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();

                break;
            case R.id.bReset:
                for (int i = 0; i < noOfSemesters; i++) {
                    gpas[i].setText("");
                }
                break;
        }
    }

    class LoadInfoInDB extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
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
                Intent savedGPs = new Intent(getApplicationContext(), SavedGPs.class);
                startActivity(savedGPs);
                break;
        }

        return super.onOptionsItemSelected(item);
    }


}
