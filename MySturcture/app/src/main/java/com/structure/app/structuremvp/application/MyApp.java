package com.structure.app.structuremvp.application;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.multidex.MultiDexApplication;

import com.structure.app.structuremvp.BuildConfig;
import com.structure.app.structuremvp.model.bean.DaoMaster;
import com.structure.app.structuremvp.model.bean.DaoSession;
import com.structure.app.structuremvp.netcom.retrofit.RetrofitHolder;

import org.greenrobot.greendao.database.Database;

import java.lang.reflect.Method;

/**
 * Created by ashishthakur on 14/1/19.
 */

public class MyApp extends MultiDexApplication {
    private static boolean activityVisible;
    private DaoSession daoSession;


    static MyApp instance;

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
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "myApp-db");
        Database db = helper.getWritableDb();

        // encrypted SQLCipher database
        // note: you need to add SQLCipher to your dependencies, check the build.gradle file
        // DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "notes-db-encrypted");
        // Database db = helper.getEncryptedWritableDb("encryption-key");

        daoSession = new DaoMaster(db).newSession();


    }


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


    class LifeCycle implements Application.ActivityLifecycleCallbacks {

        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {

        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {
            Intent intent = activity.getIntent();
        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

        }


        @Override
        public void onActivityDestroyed(Activity activity) {

        }
    }

  /*  public static BaseRequestEntity getBaseEntity(boolean includeToken) {
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
    }*/

}
