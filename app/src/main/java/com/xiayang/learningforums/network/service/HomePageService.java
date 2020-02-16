package com.xiayang.learningforums.network.service;

import com.xiayang.learningforums.bean.Response;
import com.xiayang.learningforums.bean.homepage.Article;
import com.xiayang.learningforums.bean.homepage.CommonWebsite;
import com.xiayang.learningforums.bean.homepage.HomePageArticleList;
import com.xiayang.learningforums.bean.homepage.HomePageBanner;
import com.xiayang.learningforums.bean.homepage.HotKey;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 首页相关
 * <p>
 * Date: 2020/2/17
 * Author: aaronzzxup@gmail.com
 */
public interface HomePageService {

    /**
     * 获取首页文章列表
     *
     * @param page 页码，从零开始
     */
    @GET("article/list/{page}/json")
    Call<Response<HomePageArticleList>> getHomePageArticleList(@Path("page") int page);

    /**
     * 获取首页 Banner
     */
    @GET("banner/json")
    Call<Response<List<HomePageBanner>>> getHomePageBanners();

    /**
     * 获取常用网站
     */
    @GET("friend/json")
    Call<Response<List<CommonWebsite>>> getCommonWebsites();

    /**
     * 获取搜索热词
     */
    @GET("hotkey/json")
    Call<Response<List<HotKey>>> getHotKeys();

    /**
     * 获取置顶文章
     */
    @GET("article/top/json")
    Call<Response<List<Article>>> getTopArticles();
}
