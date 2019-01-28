package com.structure.app.structuremvp.views.activity.forgotpassword;

import android.arch.lifecycle.MutableLiveData;


import com.structure.app.structuremvp.application.MyApp;
import com.structure.app.structuremvp.databinding.ActivityForgotPasswordBinding;
import com.structure.app.structuremvp.interfaces.DialogClickListener;
import com.structure.app.structuremvp.model.bean.ForgotPasswordResponseBean;
import com.structure.app.structuremvp.netcom.retrofit.RetrofitHolder;
import com.structure.app.structuremvp.utils.AppUtils;
import com.structure.app.structuremvp.utils.Utils;
import com.structure.app.structuremvp.views.base.BaseViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by akshaydashore on 27/8/18
 */
public class ForgotViewModel extends BaseViewModel implements DialogClickListener {

    public final MutableLiveData<Integer> validator = new MutableLiveData<>();
    public final MutableLiveData<ForgotPasswordResponseBean> response_validator = new MutableLiveData<>();


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

    public void forgotPassword(ActivityForgotPasswordBinding binding) {

        String email = binding.etMail.getText().toString();

        Call<ForgotPasswordResponseBean> call = RetrofitHolder.getService().forgotPassword(MyApp.getInstance().getBaseEntity(false), email);

        call.enqueue(new Callback<ForgotPasswordResponseBean>() {
            @Override
            public void onResponse(Call<ForgotPasswordResponseBean> call, Response<ForgotPasswordResponseBean> response) {
                response_validator.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ForgotPasswordResponseBean> call, Throwable t) {
                validator.setValue(AppUtils.SERVER_ERROR);
            }
        });

    }


    @Override
    public void onDialogClick(int which, int requestCode) {

    }
}
