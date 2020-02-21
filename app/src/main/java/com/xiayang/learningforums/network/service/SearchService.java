package com.xiayang.learningforums.network.service;

import com.xiayang.learningforums.bean.Article;
import com.xiayang.learningforums.bean.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * 搜索接口
 * <p>
 * Date: 2020/2/19
 * Author: aaronzzxup@gmail.com
 */
public interface SearchService {

    /**
     * @param page 页码，拼接在链接上，从0开始。
     * @param key  搜索关键词
     */
    @FormUrlEncoded
    @POST("article/query/{page}/json")
    Call<Result<List<Article>>> searchArticles(
            @Path("page") int page,
            @Field("k") String key
    );
}
