package com.xiayang.learningforums.network;

import com.xiayang.learningforums.bean.Result;
import com.xiayang.learningforums.bean.homepage.Article;
import com.xiayang.learningforums.bean.homepage.CommonWebsite;
import com.xiayang.learningforums.bean.homepage.HomePageArticleList;
import com.xiayang.learningforums.bean.homepage.HomePageBanner;
import com.xiayang.learningforums.bean.homepage.HotKey;
import com.xiayang.learningforums.network.service.HomePageService;

import java.util.List;

import retrofit2.Call;
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

    public Call<Result<HomePageArticleList>> getHomePageArticleList(int page) {
        return homePageService.getHomePageArticleList(page);
    }

    public Call<Result<List<HomePageBanner>>> getHomePageBanners() {
        return homePageService.getHomePageBanners();
    }

    public Call<Result<List<CommonWebsite>>> getCommonWebsite() {
        return homePageService.getCommonWebsites();
    }

    public Call<Result<List<HotKey>>> getHotkeys() {
        return homePageService.getHotKeys();
    }

    public Call<Result<List<Article>>> getTopArticles() {
        return homePageService.getTopArticles();
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
