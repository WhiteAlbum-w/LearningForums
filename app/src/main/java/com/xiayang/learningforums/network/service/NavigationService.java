package com.xiayang.learningforums.network.service;
import com.xiayang.learningforums.bean.NavigationData;
import com.xiayang.learningforums.bean.Result;
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
    Call<Result<NavigationData>> getNavigationDatas();
}
