package com.xiayang.learningforums.frag;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
public class TreeDataFragment extends Fragment {
    private RecyclerView rvTreeData;
    private List<Article> datas = new ArrayList<>(); // 数据源
    private ItemTreeDataAdapter itemTreeDataAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View itemView = inflater.inflate(R.layout.fragment_tree_data, container, false);
        rvTreeData = itemView.findViewById(R.id.tree_data_rv);

        // 创建 rv 的适配器
        itemTreeDataAdapter = new ItemTreeDataAdapter(getContext(), datas);
        // 设置设配器
        rvTreeData.setAdapter(itemTreeDataAdapter);
        // 设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rvTreeData.setLayoutManager(linearLayoutManager);

        getData();
        return itemView;
    }

    private void getData() {
        NetworkManager.getInstance()
                .getTreeService()
                .getTreeArticles(0, 0)
                .enqueue(new Callback<Result<ArticleList>>() {
                    @Override
                    public void onResponse(Call<Result<ArticleList>> call, Response<Result<ArticleList>> response) {
                        Result<ArticleList> result = response.body();
                        if (result != null) {
                            datas.clear();
                            datas.addAll(result.data.datas);
                            itemTreeDataAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<Result<ArticleList>> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }
}
