package com.xiayang.learningforums.frag;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
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
    private List<Article> datas;  // RecyclerView 得数据源

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
        rvPage = view.findViewById(R.id.rv_home_page);

        datas = new ArrayList<>();
        // 创建 Recycler 得适配器
        ItemHomeAdapter adapter = new ItemHomeAdapter(getContext(), datas);
        // 设置适配器
        rvPage.setAdapter(adapter);
        // 设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        rvPage.setLayoutManager(layoutManager);
        NetworkManager.getInstance()
                .getHomePageService()
                .getHomePageArticleList(1)
                .enqueue(new Callback<Result<ArticleList>>() {
                    @Override
                    public void onResponse(Call<Result<ArticleList>> call, Response<Result<ArticleList>> response) {
                        Result<ArticleList> article = response.body();
                        // 有一些没用到的变量就不用写出来，直接链式调用下去就行
                        if (article != null) {
                            datas.clear();
                            datas.addAll(article.data.datas);
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<Result<ArticleList>> call, Throwable t) {
                        // 弹 Toast 用的 Context 需要是 ApplicationContext ，不然会造成内存泄漏
                        // 然后尽量不要在类中出现硬编码，逗号之间多加一个空格，不然会有点挤在一起
                        // 可以用快捷键 ctrl + alt + L 格式化代码，可以快速解决格式问题
                        // 这里面我已经弄了很多工具类了，以 Utils 后缀的都是
                        // Toast.makeText(getContext(), R.string.network_failed, Toast.LENGTH_SHORT).show();
                        ToastUtils.showShort(R.string.network_failed);
                    }
                });
        return view;
    }
}