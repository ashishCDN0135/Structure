package com.structure.app.structuremvp.netcom.retrofit;


import com.structure.app.structuremvp.model.bean.BaseRequestEntity;
import com.structure.app.structuremvp.model.bean.ChangePasswordBean;
import com.structure.app.structuremvp.model.bean.ForgotPasswordResponseBean;
import com.structure.app.structuremvp.model.bean.LoginResponseBean;
import com.structure.app.structuremvp.netcom.Keys;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface AppStructureAPI {


    /*
    *
    *
    * App Login api call with @Params, Email,Password.
    *
    * */
    @POST(Config.LOGIN_URL)
    public Call<LoginResponseBean> doLogin(@Body BaseRequestEntity baseRequestEntity, @Query(Keys.EMAIL) String email,
                                           @Query(Keys.PASSWORD) String password);

    /*
    *
    *
    * Forgot password api call with @Param,Email.
    *
    * */
    @POST(Config.FORGOT_PASSWORD_URL)
    public Call<ForgotPasswordResponseBean> forgotPassword(@Body BaseRequestEntity baseRequestEntity, @Query(Keys.EMAIL) String email);

    /*
    *
    *
    * Change password api call with @Param,OldPassword,NewPassword,ConfirmNewPassword,EmployeeId
    *
    * */

    @POST(Config.CHANGE_PASSWORD_URL)
    Call<ChangePasswordBean> doChangePassword(
            @Body BaseRequestEntity baseRequestEntity,
            @Query(Keys.OLD_PASSWORD) String oldPassword,
            @Query(Keys.NEW_PASSWORD) String newPassword,
            @Query(Keys.CONFIRM_NEW_PASSWORD) String confirmPassword,
            @Query(Keys.EMPLOYEE_ID) String employeeId
    );

}
