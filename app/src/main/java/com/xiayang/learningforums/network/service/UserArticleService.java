package com.xiayang.learningforums.network.service;

import com.xiayang.learningforums.bean.Result;
import com.xiayang.learningforums.bean.UserArticle;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * author : Alexander
 * e-mail : wfy19961024@gmail.com
 * date   : 2020/3/22
 * desc   : 广场相关
 */
public interface UserArticleService {

    /**
     *
     * @param page 页码拼接在url上从0开始
     * @return
     *
     * 可能出现返回 列表数据 < 每页数据，因为有自见的文章被过滤掉了。
     */
    @GET("user_article/list/{page}/json")
    Call<Result<UserArticle>> getUserArticle(@Path("page") int page);
}
