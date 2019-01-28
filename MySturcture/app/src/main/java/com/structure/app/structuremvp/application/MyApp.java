package com.structure.app.structuremvp.application;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.multidex.MultiDexApplication;

import com.structure.app.structuremvp.BuildConfig;
import com.structure.app.structuremvp.model.bean.BaseRequestEntity;
import com.structure.app.structuremvp.model.bean.DaoMaster;
import com.structure.app.structuremvp.model.bean.DaoSession;
import com.structure.app.structuremvp.netcom.Keys;
import com.structure.app.structuremvp.netcom.retrofit.RetrofitHolder;
import com.structure.app.structuremvp.preferences.Pref;
import com.structure.app.structuremvp.utils.Utils;

import org.greenrobot.greendao.database.Database;

import java.lang.reflect.Method;

/**
 * Application class for the app manage dao,context etc.
 */

public class MyApp extends MultiDexApplication {
    static MyApp instance;
    private static boolean activityVisible;
    private DaoSession daoSession;

    public static MyApp getInstance() {
        if (instance == null)
            instance = new MyApp();

        return instance;
    }

    public static boolean isActivityVisible() {
        return activityVisible;
    }

    public static void activityResumed() {
        activityVisible = true;
    }

    public static void activityPaused() {
        activityVisible = false;
    }

    /*
    *
    *
    * Method to set App body for api call..
    *
    * */
    public static BaseRequestEntity getBaseEntity(boolean includeToken) {
        BaseRequestEntity baseRequestEntity = new BaseRequestEntity();
        baseRequestEntity.setApi_key(Keys.API_KEY);
        baseRequestEntity.setDevice_id(Utils.getDeviceID());
        baseRequestEntity.setDevice_token("dfsfsdfsdfsdf"); //put firebase app token here from preferences
        baseRequestEntity.setDevice_type(Keys.TYPE_ANDROID);
        if (includeToken) {
            baseRequestEntity.setToken_type(Keys.TOKEN_TYPE);
            baseRequestEntity.setAccess_token(Pref.getAccessToken(instance));
        }
        return baseRequestEntity;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = MyApp.this;

        try {

            enableStricMode();
            instantiateFabric();


            /**
             * init retrofit client to call network services
             */
            RetrofitHolder retrofitHolder = new RetrofitHolder(instance);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        /*
        *
        * Initilizing Dao for the app
        *
        * */
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "myApp-db");
        Database db = helper.getWritableDb();

        // encrypted SQLCipher database
        // note: you need to add SQLCipher to your dependencies, check the build.gradle file
        // DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "notes-db-encrypted");
        // Database db = helper.getEncryptedWritableDb("encryption-key");

        daoSession = new DaoMaster(db).newSession();


    }

    /*
    *
    *  Get Dao Session by creating application class context for dataabse query.
    *
    * */
    public DaoSession getDaoSession() {
        return daoSession;
    }

    private void instantiateFabric() {
        try {
            if (!BuildConfig.DEBUG) {
//                Fabric.with(this, new Crashlytics());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void enableStricMode() {
        try {

            if (Build.VERSION.SDK_INT >= 24) {
                try {
                    Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");
                    m.invoke(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
