package com.victor.design.app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Calculator.db";
    private static final String DATABASE_GPA_TABLE_NAME = "gpa";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + DATABASE_GPA_TABLE_NAME + " ("
                + "_id INT PRIMARY_KEY AUTOINCREMENT,"
                + "name TEXT"
                + "result "
                + "noOfCourses INT(2)"
                + ");";
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DATABASE_GPA_TABLE_NAME + ";");
        onCreate(sqLiteDatabase);
    }

}
