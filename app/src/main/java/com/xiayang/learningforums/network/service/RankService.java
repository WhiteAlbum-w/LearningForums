package com.xiayang.learningforums.network.service;

import com.xiayang.learningforums.bean.Rank;

import retrofit2.Call;
import retrofit2.adapter.rxjava2.Result;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * desc   : 积分
 * <p>
 * author : Alexander
 * e-mail : wfy19961024@gmail.com
 * date   : 2020/4/8
 */
public interface RankService {

    @GET("coin/rank/{page}/json")
    Call<Result<Rank>> getRanks(@Path("page") int page);
}
