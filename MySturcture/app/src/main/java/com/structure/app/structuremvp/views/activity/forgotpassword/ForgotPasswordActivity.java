package com.structure.app.structuremvp.views.activity.forgotpassword;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.structure.app.structuremvp.R;
import com.structure.app.structuremvp.databinding.ActivityForgotPasswordBinding;
import com.structure.app.structuremvp.model.bean.ForgotPasswordResponseBean;
import com.structure.app.structuremvp.utils.AppUtils;
import com.structure.app.structuremvp.utils.Utils;
import com.structure.app.structuremvp.views.base.BaseActivity;

public class ForgotPasswordActivity extends BaseActivity {

    ActivityForgotPasswordBinding binding;
    ForgotViewModel viewModel;
    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeViews();
        context = this;
    }

    @Override
    public void initializeViews() {

        binding = DataBindingUtil.setContentView(this, R.layout.activity_forgot_password);
        binding.tvSubmit.setOnClickListener(this);
        viewModel = ViewModelProviders.of(this).get(ForgotViewModel.class);
        binding.setViewModel(viewModel);
        viewModel.validator.observe(this, observer);
        viewModel.response_validator.observe(this, response_observer);
    }

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
            }
        }
    };


    Observer<ForgotPasswordResponseBean> response_observer = new Observer<ForgotPasswordResponseBean>() {

        @Override
        public void onChanged(@Nullable ForgotPasswordResponseBean loginBean) {

            if (loginBean == null) {
                Utils.showAlert(context, getString(R.string.error), getString(R.string.enter_employeeid), "ok", "", AppUtils.dialog_ok_click, viewModel);
            }

        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_submit:
                if (viewModel.checkEmail(binding.etMail.getText().toString())) {
                    viewModel.forgotPassword(binding);
                }
                break;
        }
    }


}
