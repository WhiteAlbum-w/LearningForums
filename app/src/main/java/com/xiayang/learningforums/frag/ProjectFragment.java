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

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectFragment extends Fragment {

    private RecyclerView rvProject;
    private List<Article> datas; //recycler 的数据源
    private ItemProjectAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project, container, false);
        rvProject = view.findViewById(R.id.project_rv);
        datas = new ArrayList<>();
        // 创建适配器
        adapter = new ItemProjectAdapter(getContext(), datas);
        // 设置适配器
        rvProject.setAdapter(adapter);
        // 设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),
                RecyclerView.VERTICAL, false);
        rvProject.setLayoutManager(layoutManager);

//        NetworkManager.getInstance()
//                .getProjectService()
//                .getProjectDatas(1,0)
//                .enqueue(new Callback<Result<ArticleList>>() {
//                    @Override
//                    public void onResponse(Call<Result<ArticleList>> call,
//                                           Response<Result<ArticleList>> response) {
//                        Result<ArticleList> article = response.body();
//                        if (article != null) {
//                            datas.clear();
//                            datas.addAll(article.data.datas);
//                            adapter.notifyDataSetChanged();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<Result<ArticleList>> call, Throwable t) {
//                        ToastUtils.showShort("网络加载失败");
//                    }
//                });
        return view;
    }
}
