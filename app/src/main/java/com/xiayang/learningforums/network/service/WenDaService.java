package com.xiayang.learningforums.network.service;

import com.xiayang.learningforums.bean.Result;
import com.xiayang.learningforums.bean.WenDa;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * desc   : 问答
 * <p>
 * author : Alexander
 * e-mail : wfy19961024@gmail.com
 * date   : 2020/4/2
 */
public interface WenDaService {

    /**
     *
     * @param page 拼接在链接上，例如1
     * @return
     */
    @GET("wenda/list/{page}/json")
    Call<Result<WenDa>> getWenDa(@Path("page") int page);
}
