package com.structure.app.structuremvp.views.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.structure.app.structuremvp.R;
import com.structure.app.structuremvp.views.activity.home.HomeActivity;
import com.structure.app.structuremvp.views.activity.login.LoginActivity;


/**
 * Created by ankurrawal on 22/8/18.
 */
public class SplashActivity extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.splash_activity);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(context, HomeActivity.class));
            }
        }, 3000);
    }

}
