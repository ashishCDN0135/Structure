package com.structure.app.structuremvp.db;

import android.annotation.SuppressLint;
import android.content.Context;

import com.structure.app.structuremvp.application.MyApp;


@SuppressWarnings("ALL")
public class DbHalper extends CRUDHelper {
    @SuppressLint("StaticFieldLeak")
    public static DbHalper dbHalper;

    public static DbHalper getInstance(Context context) {
        if (dbHalper == null) {
            if(context==null){
                context= MyApp.getInstance().getApplicationContext();
            }
            dbHalper = new DbHalper(context);
        }
        return dbHalper;
    }

    public DbHalper(Context context) {
        super(context);
    }


}