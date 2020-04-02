package com.xiayang.learningforums.frag;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xiayang.learningforums.R;
import com.xiayang.learningforums.bean.Article;
import com.xiayang.learningforums.bean.ArticleList;
import com.xiayang.learningforums.bean.Result;
import com.xiayang.learningforums.network.NetworkManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private RecyclerView rvPage;
    private SmartRefreshLayout refreshLayout;  // 刷新的控件

    private List<Article> datas;  // RecyclerView 得数据源
    private ItemHomeAdapter adapter;

    private int page = 0;


    @SuppressLint("CheckResult")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
        rvPage = view.findViewById(R.id.rv_home_page);

        datas = new ArrayList<>();
        // 创建 Recycler 得适配器
        adapter = new ItemHomeAdapter(getContext(), datas);
        // 设置适配器
        rvPage.setAdapter(adapter);
        // 设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        rvPage.setLayoutManager(layoutManager);

        getData(); //进入软件时先调用加载数据

        refreshLayout = view.findViewById(R.id.home_refresh_layout);
        //是否在加载完成时滚动列表显示新的内容
        refreshLayout.setEnableScrollContentWhenLoaded(true);
        //设置是否启用上拉加载更多（默认启用）
        refreshLayout.setEnableLoadMore(true);
        //内容不满一页时不能开启上拉加载功能
        refreshLayout.setEnableLoadMoreWhenContentNotFull(true);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getData();
                refreshLayout.finishRefresh(500);//传入false表示刷新失败
            }
        });
        //上拉监听
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                getData();
                refreshLayout.finishLoadMore(500);
            }
        });

        return view;
    }

    private void getData() {
        NetworkManager.getInstance()
                .getHomePageService()
                .getHomePageArticleList(page)
                .enqueue(new Callback<Result<ArticleList>>() {
                    @Override
                    public void onResponse(Call<Result<ArticleList>> call, Response<Result<ArticleList>> response) {
                        Result<ArticleList> article = response.body();
                        if (article != null) {
                            datas.addAll(article.data.datas);
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<Result<ArticleList>> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }
}