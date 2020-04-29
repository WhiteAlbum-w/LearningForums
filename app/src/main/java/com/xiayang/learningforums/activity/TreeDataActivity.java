package com.xiayang.learningforums.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xiayang.learningforums.R;
import com.xiayang.learningforums.bean.Article;
import com.xiayang.learningforums.bean.ArticleList;
import com.xiayang.learningforums.bean.Result;
import com.xiayang.learningforums.frag.ItemTreeDataAdapter;
import com.xiayang.learningforums.network.NetworkManager;
import com.xiayang.learningforums.utils.StatusBarUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TreeDataActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView tvTitle;
    private RecyclerView rv;
    private List<Article> datas = new ArrayList<>(); // 数据源
    private ItemTreeDataAdapter itemTreeDataAdapter;
    private int id;
    private int page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_data);

        StatusBarUtils.setColor(this,getResources().getColor(R.color.colorPrimary));

        toolbar = findViewById(R.id.tree_data_toolbar);
        tvTitle = findViewById(R.id.tree_data_title);
        rv = findViewById(R.id.tree_data_recycler);


        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // 设置标题
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        id = intent.getIntExtra("id", 0);
        tvTitle.setText(title);

        // 创建 recycler 适配器
        itemTreeDataAdapter = new ItemTreeDataAdapter(this, datas);
        // 设置适配器
        rv.setAdapter(itemTreeDataAdapter);
        // 创建布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        // 设置布局管理器
        rv.setLayoutManager(layoutManager);

        getData();

        RefreshLayout refreshLayout = findViewById(R.id.tree_data_refresh);
        refreshLayout.setEnableLoadMoreWhenContentNotFull(false);//取消内容不满一页时开启上拉加载功能
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                //datas.clear();
                if (page < 0) {
                    getData();
                } else {
                    refreshLayout.finishRefresh();
                }

                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败

            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                page++;
                getData();
                refreshlayout.finishLoadMore(200/*,false*/);//传入false表示加载失败
            }
        });
    }

    private void getData() {
        NetworkManager.getInstance()
                .getTreeService()
                .getTreeArticles(page, id)
                .enqueue(new Callback<Result<ArticleList>>() {
                    @Override
                    public void onResponse(Call<Result<ArticleList>> call, Response<Result<ArticleList>> response) {
                        Result<ArticleList> result = response.body();

                        if (result != null) {
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
