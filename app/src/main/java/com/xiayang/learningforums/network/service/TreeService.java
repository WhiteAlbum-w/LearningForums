package com.xiayang.learningforums.network.service;

import com.xiayang.learningforums.bean.ArticleList;
import com.xiayang.learningforums.bean.Response;
import com.xiayang.learningforums.bean.tree.TreeData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 体系相关接口
 * <p>
 * Date: 2020/2/18
 * Author: aaronzzxup@gmail.com
 */
public interface TreeService {

    /**
     * 体系数据接口
     * 主要标识的网站内容的体系结构，二级目录。
     */
    @GET("tree/json")
    Call<Response<List<TreeData>>> getTreeDatas();

    /**
     * 知识体系下的文章
     *
     * @param page 页码
     * @param id   分类的id，上述二级目录的id
     */
    @GET("article/list/{page}/json?cid={id}")
    Call<Response<ArticleList>> getTreeArticles(@Path("page") int page, @Path("id") int id);

    /**
     * 按照作者昵称搜索文章
     *
     * @param page   拼接在链接上，从0开始。
     * @param author 作者昵称，不支持模糊匹配。
     */
    @GET("article/list/{page}/json?author={author}")
    Call<Response<ArticleList>> getAuthorArticles(@Path("page") int page, @Path("author") String author);
}
