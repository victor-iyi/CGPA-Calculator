package com.victor.design.app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class Message {

    private static boolean returnValue;

    public static void show(Context context, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(msg);
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public static boolean show(Context context, String msg, String posMsg, String negMsg) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(msg);
        builder.setCancelable(false);
        builder.setPositiveButton(posMsg, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                returnValue = true;
                dialogInterface.cancel();
            }
        });
        builder.setNegativeButton(negMsg, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                returnValue = false;
                dialogInterface.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();

        return returnValue;
    }

}
