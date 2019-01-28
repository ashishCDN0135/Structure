package com.structure.app.structuremvp.views.activity.login;

import android.arch.lifecycle.MutableLiveData;


import com.google.gson.Gson;
import com.structure.app.structuremvp.R;
import com.structure.app.structuremvp.application.MyApp;
import com.structure.app.structuremvp.databinding.ActivityLoginBinding;
import com.structure.app.structuremvp.interfaces.DialogClickListener;
import com.structure.app.structuremvp.model.bean.LoginResponseBean;
import com.structure.app.structuremvp.netcom.retrofit.RetrofitHolder;
import com.structure.app.structuremvp.utils.AppUtils;
import com.structure.app.structuremvp.utils.Connectivity;
import com.structure.app.structuremvp.utils.Utils;
import com.structure.app.structuremvp.views.base.BaseViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends BaseViewModel implements DialogClickListener {

    public final MutableLiveData<Integer> validator = new MutableLiveData<>();
    public final MutableLiveData<LoginResponseBean> response_validator = new MutableLiveData<>();


    public boolean checkEmail(String text) {

        if (Utils.isStringsEmpty(text)) {
            validator.setValue(AppUtils.empty_id);
            return false;
        } else if (!Utils.isValideEmail(text)) {
            validator.setValue(AppUtils.invalid_mail);
            return false;
        }
        return true;
    }


    public boolean checkPassword(String text) {

        if (Utils.isStringsEmpty(text)) {
            validator.setValue(AppUtils.empty_password);
            return false;
        }
        return true;
    }


    @Override
    public void onDialogClick(int which, int requestCode) {

    }


    public boolean validateLogin(ActivityLoginBinding binding) {
        if (!Connectivity.isConnected()) {
            validator.setValue(AppUtils.NO_INTERNET);
            return false;
        } else if (!checkEmail(binding.etEmail.getText().toString())) {
            return false;
        } else if (!checkPassword(binding.etPassword.getText().toString())) {
            return false;
        } else {
            return true;
        }

    }

    public void doLogin(ActivityLoginBinding binding) {
         String email = binding.etEmail.getText().toString();
        String password = binding.etPassword.getText().toString();

        Call<LoginResponseBean> call = RetrofitHolder.getService().doLogin(MyApp.getInstance().getBaseEntity(false), email, password);
        Utils.showLog(AppUtils.REQUEST, "" + call.request().url());
        call.enqueue(new Callback<LoginResponseBean>() {
            @Override
            public void onResponse(Call<LoginResponseBean> call, Response<LoginResponseBean> response) {
                Utils.showLog(AppUtils.RESPONSE, "" + new Gson().toJson(response.body()));

                Utils.hideProgressDialog();
                response_validator.setValue(response.body());
            }

            @Override
            public void onFailure(Call<LoginResponseBean> call, Throwable t) {
                Utils.hideProgressDialog();
                validator.setValue(AppUtils.SERVER_ERROR);
            }
        });
    }

}
