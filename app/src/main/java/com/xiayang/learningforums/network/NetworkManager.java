package com.xiayang.learningforums.network;

import com.xiayang.learningforums.network.service.HomePageService;
import com.xiayang.learningforums.network.service.NavigationService;
import com.xiayang.learningforums.network.service.TreeService;

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
    private TreeService treeService;
    private NavigationService navigationService;

    public static NetworkManager getInstance() {
        return Holder.INSTANCE;
    }

    private NetworkManager() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        homePageService = retrofit.create(HomePageService.class);
        treeService = retrofit.create(TreeService.class);
    }

    public HomePageService getHomePageService() {
        return homePageService;
    }

    public TreeService getTreeService() {
        return treeService;
    }

    public NavigationService getNavigationService() {
        return navigationService;
    }

    private static class Holder {
        private static final NetworkManager INSTANCE = new NetworkManager();
    }
}
