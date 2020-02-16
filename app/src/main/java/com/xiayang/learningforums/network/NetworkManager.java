package com.xiayang.learningforums.network;

import com.xiayang.learningforums.bean.Response;
import com.xiayang.learningforums.bean.homepage.Article;
import com.xiayang.learningforums.bean.homepage.CommonWebsite;
import com.xiayang.learningforums.bean.homepage.HomePageArticleList;
import com.xiayang.learningforums.bean.homepage.HomePageBanner;
import com.xiayang.learningforums.bean.homepage.HotKey;
import com.xiayang.learningforums.network.service.HomePageService;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络请求单例
 * <p>
 * Date: 2020/2/17
 * Author: aaronzzxup@gmail.com
 */
public final class NetworkManager {

    private static final String BASE_URL = "https://www.wanandroid.com/";

    private HomePageService homePageService;

    public static NetworkManager getInstance() {
        return Holder.INSTANCE;
    }

    public void getHomePageArticleList(int page, Callback<Response<HomePageArticleList>> callback) {
        homePageService.getHomePageArticleList(page).enqueue(callback);
    }

    public void getHomePageBanners(Callback<Response<List<HomePageBanner>>> callback) {
        homePageService.getHomePageBanners().enqueue(callback);
    }

    public void getCommonWebsite(Callback<Response<List<CommonWebsite>>> callback) {
        homePageService.getCommonWebsites().enqueue(callback);
    }

    public void getHotkeys(Callback<Response<List<HotKey>>> callback) {
        homePageService.getHotKeys().enqueue(callback);
    }

    public void getTopArticles(Callback<Response<List<Article>>> callback) {
        homePageService.getTopArticles().enqueue(callback);
    }

    private NetworkManager() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        homePageService = retrofit.create(HomePageService.class);
    }

    private static class Holder {
        private static final NetworkManager INSTANCE = new NetworkManager();
    }
}
