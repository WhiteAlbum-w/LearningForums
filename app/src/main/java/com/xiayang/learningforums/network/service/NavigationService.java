package com.xiayang.learningforums.network.service;

import com.xiayang.learningforums.bean.Response;
import com.xiayang.learningforums.bean.navigation.NavigationData;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * 导航接口
 * <p>
 * Date: 2020/2/19
 * Author: aaronzzxup@gmail.com
 */
public interface NavigationService {

    @GET("navi/json")
    Call<Response<NavigationData>> getNavigationDatas();
}
