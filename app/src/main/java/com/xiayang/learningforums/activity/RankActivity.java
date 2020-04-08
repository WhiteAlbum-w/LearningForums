package com.xiayang.learningforums.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xiayang.learningforums.R;
import com.xiayang.learningforums.bean.Rank;
import com.xiayang.learningforums.bean.RankList;
import com.xiayang.learningforums.frag.ItemRankAdapter;
import com.xiayang.learningforums.network.NetworkManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.adapter.rxjava2.Result;

import static com.xiayang.learningforums.App.getContext;

public class RankActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView rv;
    private int page = 1;
    private List<RankList> ranks = new ArrayList<>(); // RecyclerView 的数据源
    private ItemRankAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);

        toolbar = findViewById(R.id.rank_toolbar);
        rv = findViewById(R.id.rank_rv);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }

        // 创建 RecyclerView 得适配器
        adapter = new ItemRankAdapter(getContext(), ranks);
        // 设置适配器
        rv.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(linearLayoutManager);

        getData();
    }

    private void getData() {
        NetworkManager.getInstance()
                .getRankService()
                .getRanks(page)
                .enqueue(new Callback<Result<Rank>>() {
                    @Override
                    public void onResponse(Call<Result<Rank>> call, Response<Result<Rank>> response) {

                    }

                    @Override
                    public void onFailure(Call<Result<Rank>> call, Throwable t) {

                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
