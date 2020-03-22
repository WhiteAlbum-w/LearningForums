package com.xiayang.learningforums.network.service;

import com.xiayang.learningforums.bean.Article;
import com.xiayang.learningforums.bean.ArticleList;
import com.xiayang.learningforums.bean.CommonWebsite;
import com.xiayang.learningforums.bean.HomePageBanner;
import com.xiayang.learningforums.bean.HotKey;
import com.xiayang.learningforums.bean.Result;

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
     * 很多 H5 页面会恶意跳转淘宝等，可以在 WebView 的 shouldOverrideUrlLoading
     * 中做一下拦截，非常影响用户体验。
     * 注意：页码从 0 开始，拼接在链接上。
     * 其中有两个易混淆的字段:
     * "superChapterId": 153,
     * "superChapterName": "framework"，一级分类的名称
     * superChapterId 其实不是一级分类 id ，因为要拼接跳转 url ，内容实际都挂在二级分类下，
     * 所以该 id 实际上是一级分类的第一个子类目的id，拼接后故可正常跳转。
     * 有两个字段比较容易混淆：
     * author 与 shareUser
     * 网站上的文章可能是某位作者 author 的，也可能是某位分享人 shareUser 分享的。
     * 如果是分享人分享的，author 为 null。
     * 注意：除了文字标题，链接，其他字段都可能为 null，一定要注意布局下发 null 时的显示情况。
     * <p>
     * @param page 页码，从零开始
     */
    @GET("article/list/{page}/json")
    Call<Result<ArticleList>> getHomePageArticleList(@Path("page") int page);

    /**
     * 获取首页 Banner
     */
    @GET("banner/json")
    Call<Result<List<HomePageBanner>>> getHomePageBanners();

    /**
     * 获取常用网站
     */
    @GET("friend/json")
    Call<Result<List<CommonWebsite>>> getCommonWebsites();

    /**
     * 获取搜索热词
     * 即目前搜索最多的关键词。
     */
    @GET("hotkey/json")
    Call<Result<List<HotKey>>> getHotKeys();

    /**
     * 获取置顶文章
     */
    @GET("article/top/json")
    Call<Result<List<Article>>> getTopArticles();
}
