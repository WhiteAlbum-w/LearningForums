package com.xiayang.learningforums.frag;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xiayang.learningforums.R;
import com.xiayang.learningforums.bean.Article;
import com.xiayang.learningforums.bean.Result;
import com.xiayang.learningforums.bean.WenDa;
import com.xiayang.learningforums.network.NetworkManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment extends Fragment {
    private RecyclerView rvQuestion;
    private List<Article> wenda = new ArrayList<>(); // 问答数据源
    private ItemQuestionAdapter adapter;
    private int page = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View itemView = inflater.inflate(R.layout.fragment_question, container, false);
        rvQuestion = itemView.findViewById(R.id.question_recycler);

        // 创建适配器
        adapter = new ItemQuestionAdapter(getContext(), wenda);
        // 设置适配器
        rvQuestion.setAdapter(adapter);
        // 创建布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        // 设置布局管理器
        rvQuestion.setLayoutManager(linearLayoutManager);

        getData();

        RefreshLayout refreshLayout = itemView.findViewById(R.id.question_refresh);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getData();
                refreshLayout.finishRefresh(500);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                getData();
                refreshLayout.finishLoadMore(500);
            }
        });
        return itemView;
    }

    private void getData() {
        NetworkManager.getInstance()
                .getWenDaService()
                .getWenDa(page)
                .enqueue(new Callback<Result<WenDa>>() {
                    @Override
                    public void onResponse(Call<Result<WenDa>> call, Response<Result<WenDa>> response) {
                        Result<WenDa> result = response.body();
                        if (result != null) {
                            wenda.addAll(result.data.datas);
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<Result<WenDa>> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }
}
