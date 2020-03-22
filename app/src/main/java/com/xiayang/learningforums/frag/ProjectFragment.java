package com.xiayang.learningforums.frag;

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
public class ProjectFragment extends Fragment {

    private RecyclerView rvProject;
    private List<Article> datas; //recycler 的数据源
    private ItemProjectAdapter adapter;
    private SmartRefreshLayout refreshLayout;
    private int page = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project, container, false);
        rvProject = view.findViewById(R.id.project_rv);
        refreshLayout = view.findViewById(R.id.project_refresh);

        datas = new ArrayList<>();
        // 创建适配器
        adapter = new ItemProjectAdapter(getContext(), datas);
        // 设置适配器
        rvProject.setAdapter(adapter);
        // 设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),
                RecyclerView.VERTICAL, false);
        rvProject.setLayoutManager(layoutManager);

        getData(); //进入软件先刷新

        // 下拉刷新监听
        refreshLayout.setOnRefreshListener(refreshLayout -> {
            getData();
            refreshLayout.finishRefresh(500);
        });
        // 上拉加载监听
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
                .getProjectService()
                .getProjectDatas(page, 1)
                .enqueue(new Callback<Result<ArticleList>>() {
                    @Override
                    public void onResponse(Call<Result<ArticleList>> call,
                                           Response<Result<ArticleList>> response) {
                        Result<ArticleList> article = response.body();
                        if (article != null) {
//                            datas.clear();
                            datas.addAll(article.data.datas);
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<Result<ArticleList>> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }
}
