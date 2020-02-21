package com.xiayang.learningforums.network.service;

import com.xiayang.learningforums.bean.Result;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * 登录与注册接口
 * <p>
 * Date: 2020/2/19
 * Author: aaronzzxup@gmail.com
 */
public interface LoginService {

    @FormUrlEncoded
    @POST("user/login")
    Call<Result<Object>> login(
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("user/register")
    Call<Result<Object>> register(
            @Field("username") String username,
            @Field("password") String password,
            @Field("repassword") String repassword
    );

    @GET("user/logout/json")
    Call<Result<Object>> logout();
}
