package com.structure.app.structuremvp.netcom.retrofit;


import com.structure.app.structuremvp.model.bean.BaseRequestEntity;
import com.structure.app.structuremvp.model.bean.LoginResponseBean;
import com.structure.app.structuremvp.netcom.Keys;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by ankurrawal on 22/8/18.
 */

public interface KeyKeepAPI {

    @POST(Config.LOGIN_URL)
    public Call<LoginResponseBean> doLogin(@Body BaseRequestEntity baseRequestEntity,
                                           @Query(Keys.EMAIL) String email,
                                           @Query(Keys.PASSWORD) String password);





}
