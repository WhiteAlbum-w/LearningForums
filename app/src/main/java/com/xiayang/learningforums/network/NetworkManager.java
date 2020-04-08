package com.xiayang.learningforums.network;

import com.xiayang.learningforums.network.service.HomePageService;
import com.xiayang.learningforums.network.service.LoginService;
import com.xiayang.learningforums.network.service.NavigationService;
import com.xiayang.learningforums.network.service.ProjectService;
import com.xiayang.learningforums.network.service.RankService;
import com.xiayang.learningforums.network.service.SearchService;
import com.xiayang.learningforums.network.service.TreeService;
import com.xiayang.learningforums.network.service.UserArticleService;
import com.xiayang.learningforums.network.service.WenDaService;

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
    private ProjectService projectService;
    private LoginService loginService;
    private SearchService searchService;
    private UserArticleService userArticleService;
    private WenDaService wenDaService;
    private RankService rankService;

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
        navigationService = retrofit.create(NavigationService.class);
        projectService = retrofit.create(ProjectService.class);
        loginService = retrofit.create(LoginService.class);
        searchService = retrofit.create(SearchService.class);
        userArticleService = retrofit.create(UserArticleService.class);
        wenDaService = retrofit.create(WenDaService.class);
        rankService = retrofit.create(RankService.class);
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

    public ProjectService getProjectService() {
        return projectService;
    }

    public LoginService getLoginService() {
        return loginService;
    }

    public SearchService getSearchService() {
        return searchService;
    }

    public UserArticleService getUserArticleService() {
        return userArticleService;
    }

    public WenDaService getWenDaService() {
        return wenDaService;
    }

    public RankService getRankService() {
        return rankService;
    }

    private static class Holder {
        private static final NetworkManager INSTANCE = new NetworkManager();
    }
}
