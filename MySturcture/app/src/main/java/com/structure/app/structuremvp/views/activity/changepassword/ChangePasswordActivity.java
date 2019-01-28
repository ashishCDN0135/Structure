package com.structure.app.structuremvp.views.activity.changepassword;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.structure.app.structuremvp.R;
import com.structure.app.structuremvp.databinding.ActivityChangePasswordBinding;
import com.structure.app.structuremvp.interfaces.DialogClickListener;
import com.structure.app.structuremvp.model.bean.ChangePasswordBean;
import com.structure.app.structuremvp.utils.AppUtils;
import com.structure.app.structuremvp.utils.Utils;
import com.structure.app.structuremvp.views.base.BaseActivity;
import com.structure.app.structuremvp.views.customview.CustomActionBar;

public class ChangePasswordActivity extends BaseActivity implements DialogClickListener {

    private ActivityChangePasswordBinding binding;
    private ChangePasswordViewModel viewModel;
    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setCustomActionBar();
        initializeViews();
    }


    @Override
    public void setCustomActionBar() {
        super.setCustomActionBar();
        CustomActionBar customActionBar = new CustomActionBar(this);
        customActionBar.setActionbar(getString(R.string.change_password), true, false, this);
    }

    @Override
    public void initializeViews() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_change_password);
        binding.tvSubmit.setOnClickListener(this);
        viewModel = ViewModelProviders.of(this).get(ChangePasswordViewModel.class);
        binding.setViewModel(viewModel);
        viewModel.validator.observe(this, observer);

        viewModel.response_validator.observe(this, response_observer);

    }

    /**
     * handle web service response
     */
    Observer<ChangePasswordBean> response_observer = new Observer<ChangePasswordBean>() {
        @Override
        public void onChanged(@Nullable ChangePasswordBean bean) {

            if (bean == null) {
                Utils.showAlert(context, "", getString(R.string.server_error), "ok", "", AppUtils.dialog_ok_click, ChangePasswordActivity.this);
                return;
            }

            if (bean.getCode().equals(AppUtils.STATUS_SUCCESS)) {
                Utils.showAlert(context, "", bean.getMessage(), "ok", "", AppUtils.dialog_request_succes, ChangePasswordActivity.this);
            } else if (bean.getCode().equals(AppUtils.STATUS_FAIL)) {
                Utils.showAlert(context, "", bean.getMessage(), "ok", "", AppUtils.dialog_ok_click, ChangePasswordActivity.this);
                return;
            }
        }
    };


    Observer observer = new Observer<Integer>() {

        @Override
        public void onChanged(@Nullable Integer value) {

            switch (value) {

                case AppUtils.empty_old_password:
                    Utils.showToast(context, getString(R.string.enter_old_password));
                    break;

                case AppUtils.empty_password:
                    Utils.showToast(context, getString(R.string.enter_password));
                    break;

                case AppUtils.empty_confirm_password:
                    Utils.showToast(context, getString(R.string.enter_confirm_password));
                    break;

                case AppUtils.match_confirm_password:
                    Utils.showToast(context, getString(R.string.confirm_password_match));
                    break;
                case AppUtils.SERVER_ERROR:
                    Utils.showToast(context, getString(R.string.server_error));
                    break;

                case AppUtils.NO_INTERNET:
                    Utils.hideProgressDialog();
                    Utils.showToast(context, getString(R.string.internet_connection));
                    break;

            }
        }
    };


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.tv_submit:
                if (viewModel.isValidate(binding)) {
                    Utils.showDialog(context,getString(R.string.please_wait));
                    viewModel.doChangePassword(binding, context);
                }
                break;

            case R.id.left_iv:
                finish();
                break;
        }
    }


    @Override
    public void onDialogClick(int which, int requestCode) {

        switch (which) {
            case AppUtils.dialog_request_succes:
                finish();
                break;
        }
    }

}
