package com.xiayang.learningforums.network.service;

import com.xiayang.learningforums.bean.Response;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * 登录与注册接口
 * <p>
 * Date: 2020/2/19
 * Author: aaronzzxup@gmail.com
 */
public interface LoginService {

    @POST("user/login")
    Call<Response<Object>> login(
            @Field("username") String username,
            @Field("password") String password
    );

    @POST("user/register")
    Call<Response<Object>> register(
            @Field("username") String username,
            @Field("password") String password,
            @Field("repassword") String repassword
    );

    @GET("user/logout/json")
    Call<Response<Object>> logout();
}
