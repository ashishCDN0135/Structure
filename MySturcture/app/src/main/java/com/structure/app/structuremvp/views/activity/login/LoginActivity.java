package com.structure.app.structuremvp.views.activity.login;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;
import com.structure.app.structuremvp.R;
import com.structure.app.structuremvp.application.MyApp;
import com.structure.app.structuremvp.databinding.ActivityLoginBinding;
import com.structure.app.structuremvp.model.bean.LoginResponseBean;
import com.structure.app.structuremvp.preferences.Pref;
import com.structure.app.structuremvp.utils.AppUtils;
import com.structure.app.structuremvp.utils.Utils;
import com.structure.app.structuremvp.views.activity.forgotpassword.ForgotPasswordActivity;
import com.structure.app.structuremvp.views.activity.home.HomeActivity;
import com.structure.app.structuremvp.views.base.BaseActivity;

public class LoginActivity extends BaseActivity {

    LoginViewModel viewModel;
    private ActivityLoginBinding binding;
    private Context context;
    Observer observer = new Observer<Integer>() {

        @Override
        public void onChanged(@Nullable Integer value) {
            switch (value) {

                case AppUtils.empty_id:
                    Utils.showToast(context, getString(R.string.enter_employeeid));
                    break;

                case AppUtils.empty_password:
                    Utils.showToast(context, getString(R.string.enter_password));
                    break;

                case AppUtils.invalid_mail:
                    Utils.showToast(context, getString(R.string.enter_valid_employeeid));
                    break;
                case AppUtils.NO_INTERNET:
                    Utils.showToast(context, getString(R.string.internet_connection));
                    break;

                case AppUtils.SERVER_ERROR:
                    Utils.showToast(context, getString(R.string.server_error));
                    break;
            }
        }
    };
    Observer<LoginResponseBean> response_observer = new Observer<LoginResponseBean>() {

        @Override
        public void onChanged(@Nullable LoginResponseBean loginBean) {

            if (loginBean == null) {
                Utils.showAlert(context, "", getString(R.string.server_error), getString(R.string.ok), "", AppUtils.dialog_ok_click, viewModel);
                return;
            }

            if (loginBean.getCode().equals(AppUtils.STATUS_FAIL)) {
                Utils.showAlert(context, "", loginBean.getMessage(), getString(R.string.ok), "", AppUtils.dialog_ok_click, viewModel);
                return;
            }
            Gson gson = new Gson();

            String user_detail = gson.toJson(loginBean.getResult());
            Pref.setUserDetail(context, user_detail);
            String empId = loginBean.getResult().getEmployeeId() + "";
            Pref.setEmployeeID(context, empId);
            Pref.setAccessToken(context, loginBean.getAccessToken());
            //  Pref.setBoolean(context, isRemember, Pref.REMEMBER_ME);
            Pref.setBoolean(context, true, Pref.IS_LOGIN);
            Pref.setPassword(context, binding.etPassword.getText().toString());

            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            finish();
        }
    };

    @Override
    public void initializeViews() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        binding.setViewModel(viewModel);
        binding.loginBtn.setOnClickListener(this);
        binding.signUpBtn.setOnClickListener(this);
        binding.forgotPasswordBtn.setOnClickListener(this);
        viewModel.validator.observe(this, observer);
        viewModel.response_validator.observe(this, response_observer);


    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        initializeViews();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                if (viewModel.validateLogin(binding)) {
                    Utils.showDialog(this, getString(R.string.please_wait));
                    viewModel.doLogin(binding);
                }
                break;

            case R.id.sign_up_btn:
                //  startActivity(new Intent(context, ForgotPasswordActivity.class));
                break;
            case R.id.forgot_password_btn:
                startActivity(new Intent(context, ForgotPasswordActivity.class));
                break;
        }
    }


}
