package com.xiayang.learningforums.network.service;

import com.xiayang.learningforums.bean.ArticleList;
import com.xiayang.learningforums.bean.Response;
import com.xiayang.learningforums.bean.TreeData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 项目接口
 * <p>
 * Date: 2020/2/19
 * Author: aaronzzxup@gmail.com
 */
public interface ProjectService {

    /**
     * 项目分类
     * 项目为包含一个分类，该接口返回整个分类。
     */
    @GET("project/tree/json")
    Call<Response<List<TreeData>>> getProjects();

    /**
     * 项目列表数据
     * 某一个分类下项目列表数据，分页展示
     * <p>
     *
     * @param page 页码，拼接在链接中，从1开始。
     * @param cid  分类的id，上面项目分类接口
     */
    @GET("project/list/{page}/json?cid={cid}")
    Call<Response<ArticleList>> getProjectDatas(@Path("page") int page, @Path("cid") int cid);
}
