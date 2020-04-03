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
import com.xiayang.learningforums.bean.UserArticle;
import com.xiayang.learningforums.network.NetworkManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SquareFragment extends Fragment {

    private RecyclerView rvSquare;
    private ItemSquareAdapter adapter;
//    private FloatingActionButton fab;
    private RefreshLayout refreshLayout;
    private int page = 0;

    private List<Article> datas = new ArrayList<>(); // 数据源

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View itemView = inflater.inflate(R.layout.fragment_square, container, false);

        rvSquare = itemView.findViewById(R.id.square_recycler);
        refreshLayout = itemView.findViewById(R.id.square_refresh);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getData();
                refreshLayout.finishRefresh(500/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                getData();
                refreshLayout.finishLoadMore(500/*,false*/);//传入false表示加载失败
            }
        });
//        fab = itemView.findViewById(R.id.item_square_fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getContext(), "未添加功能", Toast.LENGTH_SHORT).show();
//            }
//        });

        // 创建适配器
        adapter = new ItemSquareAdapter(getContext(), datas);
        // 设置适配器
        rvSquare.setAdapter(adapter);
        // 设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvSquare.setLayoutManager(linearLayoutManager);

        getData();
        return itemView;
    }

    private void getData() {
        NetworkManager.getInstance()
                .getUserArticleService()
                .getUserArticle(page)
                .enqueue(new Callback<Result<UserArticle>>() {
                    @Override
                    public void onResponse(Call<Result<UserArticle>> call, Response<Result<UserArticle>> response) {
                        Result<UserArticle> result = response.body();
                        if (result != null) {
                            datas.addAll(result.data.datas);
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<Result<UserArticle>> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }
}
