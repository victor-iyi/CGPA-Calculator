package com.victor.design.app;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.ProgressDialog;
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
import android.view.View.OnClickListener;
import android.widget.*;

import java.util.InputMismatchException;


public class CalculateGPA extends ActionBarActivity implements OnClickListener {

    private LinearLayout[] layouts;
    private TextView[] courses;
    private EditText[] grades, units;
    private int noOfCourses;
    private Button calc, reset;
    private String saveText, gpaResult;
    protected String[] saveGrade, saveUnit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculategpa);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle("GPA Calculator");
        setSupportActionBar(toolbar);

        initializeVars();
        Bundle bundle = getIntent().getExtras();
        noOfCourses = bundle.getInt("noOfCourses");
        saveGrade = new String[noOfCourses];
        saveUnit = new String[noOfCourses];

        // display relevant layout
        for (int i = 0; i < noOfCourses; i++)
            layouts[i].setVisibility(LinearLayout.VISIBLE);

    }

    private void initializeVars() {
        calc = (Button) findViewById(R.id.bCalculate);
        reset = (Button) findViewById(R.id.bReset);
        layouts = new LinearLayout[]{
                (LinearLayout) findViewById(R.id.llcourse1), (LinearLayout) findViewById(R.id.llcourse2),
                (LinearLayout) findViewById(R.id.llcourse3), (LinearLayout) findViewById(R.id.llcourse4),
                (LinearLayout) findViewById(R.id.llcourse5), (LinearLayout) findViewById(R.id.llcourse6),
                (LinearLayout) findViewById(R.id.llcourse7), (LinearLayout) findViewById(R.id.llcourse8),
                (LinearLayout) findViewById(R.id.llcourse9), (LinearLayout) findViewById(R.id.llcourse10),
                (LinearLayout) findViewById(R.id.llcourse11), (LinearLayout) findViewById(R.id.llcourse12),
                (LinearLayout) findViewById(R.id.llcourse13), (LinearLayout) findViewById(R.id.llcourse14),
                (LinearLayout) findViewById(R.id.llcourse15), (LinearLayout) findViewById(R.id.llcourse16),
                (LinearLayout) findViewById(R.id.llcourse17), (LinearLayout) findViewById(R.id.llcourse18),
                (LinearLayout) findViewById(R.id.llcourse19), (LinearLayout) findViewById(R.id.llcourse20)
        };
        grades = new EditText[]{
                (EditText) findViewById(R.id.etGrade1), (EditText) findViewById(R.id.etGrade2),
                (EditText) findViewById(R.id.etGrade3), (EditText) findViewById(R.id.etGrade4),
                (EditText) findViewById(R.id.etGrade5), (EditText) findViewById(R.id.etGrade6),
                (EditText) findViewById(R.id.etGrade7), (EditText) findViewById(R.id.etGrade8),
                (EditText) findViewById(R.id.etGrade9), (EditText) findViewById(R.id.etGrade10),
                (EditText) findViewById(R.id.etGrade11), (EditText) findViewById(R.id.etGrade12),
                (EditText) findViewById(R.id.etGrade13), (EditText) findViewById(R.id.etGrade14),
                (EditText) findViewById(R.id.etGrade15), (EditText) findViewById(R.id.etGrade16),
                (EditText) findViewById(R.id.etGrade17), (EditText) findViewById(R.id.etGrade18),
                (EditText) findViewById(R.id.etGrade19), (EditText) findViewById(R.id.etGrade20),
        };
        units = new EditText[]{
                (EditText) findViewById(R.id.etUnit1), (EditText) findViewById(R.id.etUnit2),
                (EditText) findViewById(R.id.etUnit3), (EditText) findViewById(R.id.etUnit4),
                (EditText) findViewById(R.id.etUnit5), (EditText) findViewById(R.id.etUnit6),
                (EditText) findViewById(R.id.etUnit7), (EditText) findViewById(R.id.etUnit8),
                (EditText) findViewById(R.id.etUnit9), (EditText) findViewById(R.id.etUnit10),
                (EditText) findViewById(R.id.etUnit11), (EditText) findViewById(R.id.etUnit12),
                (EditText) findViewById(R.id.etUnit13), (EditText) findViewById(R.id.etUnit14),
                (EditText) findViewById(R.id.etUnit15), (EditText) findViewById(R.id.etUnit16),
                (EditText) findViewById(R.id.etUnit17), (EditText) findViewById(R.id.etUnit18),
                (EditText) findViewById(R.id.etUnit19), (EditText) findViewById(R.id.etUnit20),
        };
        courses = new TextView[]{
                (TextView) findViewById(R.id.tvCourse1), (TextView) findViewById(R.id.tvCourse2),
                (TextView) findViewById(R.id.tvCourse3), (TextView) findViewById(R.id.tvCourse4),
                (TextView) findViewById(R.id.tvCourse5), (TextView) findViewById(R.id.tvCourse6),
                (TextView) findViewById(R.id.tvCourse7), (TextView) findViewById(R.id.tvCourse8),
                (TextView) findViewById(R.id.tvCourse9), (TextView) findViewById(R.id.tvCourse10),
                (TextView) findViewById(R.id.tvCourse11), (TextView) findViewById(R.id.tvCourse12),
                (TextView) findViewById(R.id.tvCourse13), (TextView) findViewById(R.id.tvCourse14),
                (TextView) findViewById(R.id.tvCourse15), (TextView) findViewById(R.id.tvCourse16),
                (TextView) findViewById(R.id.tvCourse17), (TextView) findViewById(R.id.tvCourse18),
                (TextView) findViewById(R.id.tvCourse19), (TextView) findViewById(R.id.tvCourse20),
        };
        setOnClicks();
    }

    private void setOnClicks() {
        calc.setOnClickListener(this);
        reset.setOnClickListener(this);
    }

    /* Save GPA method */
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
                    new LoadStuffIntoDB().execute(saveText);
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
        /* Calculate button clicked! */
        if (view.getId() == R.id.bCalculate) {
            String[] gradeString = new String[noOfCourses];
            int[] grade = new int[noOfCourses];
            int[] unit = new int[noOfCourses];
            int[] saveUnit = new int[noOfCourses];
            // Changing the grade's editText to String object
            for (int i = 0; i < noOfCourses; i++) {
                String dynamicCount = (i + 1) + "th";
                if (i == 0) dynamicCount = "1st";
                if (i == 1) dynamicCount = "2nd";
                if (i == 2) dynamicCount = "3rd";
                gradeString[i] = grades[i].getText().toString();
                saveGrade[i] = gradeString[i];
                if (gradeString[i].isEmpty()) {
                    Message.show(this, "The " + dynamicCount + " grade field is empty!");
                    return;
                }
            }

             /* User has entered an invalid grade i.e not in range of A-F */
            if (!GPAHelper.validateGrades(gradeString)) {
                Message.show(CalculateGPA.this, "Grades are in between A - F.");
                return;
            }

                /* GOOD USER!!! :) */
            // Grade Integer initializer
            for (int i = 0; i < noOfCourses; i++)
                grade[i] = GPAHelper.getGrade(gradeString[i]);

            // Units
            for (int i = 0; i < noOfCourses; i++) {
                String dynamicCount = (i + 1) + "th";
                if (i == 0) dynamicCount = "1st";
                if (i == 1) dynamicCount = "2nd";
                if (i == 2) dynamicCount = "3rd";

                //handler for a non numeric input
                String mismatchError = "Error Detected at the " + dynamicCount +
                        " unit field.\nA non-numeric number was entered.";
                try {
                    unit[i] = Integer.parseInt(units[i].getText().toString());
                    saveUnit[i] = unit[i];
                } catch (NumberFormatException e) {
                    Message.show(CalculateGPA.this, mismatchError);
                    return;
                } catch (InputMismatchException e) {
                    Message.show(this, mismatchError);
                    return;
                }
            }

            int c = 0;
            for (int i : saveUnit) {
                this.saveUnit[c] = String.valueOf(i);
                c++;
            }
                /*
                 * TADA!!!
                  * */
            double gpa = GPAHelper.calculate(grade, unit);
            gpaResult = String.valueOf(gpa);

            // dialog box showing off the gpa result and prompt to save or not.
            AlertDialog.Builder builder = new AlertDialog.Builder(CalculateGPA.this);
            builder.setMessage("Your GPA = " + gpa);
            builder.setCancelable(false);

            //save the result
            builder.setPositiveButton("Save Result", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    save();
                    dialogInterface.cancel();
                }
            });

            //don't save the result
            builder.setNegativeButton("Don't Save", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            AlertDialog alert = builder.create();
            alert.show();


        }
        /*
        * Reset Button which resets all input on the app.
        * */
        else if (view.getId() == R.id.bReset) {
            for (int i = 0; i < noOfCourses; i++) {
                grades[i].setText("");
                units[i].setText("");
            }
        }

    }

    // Loading saved Info into the DB
    class LoadStuffIntoDB extends AsyncTask<String, Integer, String> {

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
