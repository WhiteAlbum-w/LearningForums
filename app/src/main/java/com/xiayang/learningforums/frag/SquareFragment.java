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
import com.xiayang.learningforums.bean.Result;
import com.xiayang.learningforums.bean.User_Article;
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

    private List<Article> datas = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View itemView = inflater.inflate(R.layout.fragment_square, container, false);

        rvSquare = itemView.findViewById(R.id.square_recycler);
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
                .getUserArticle(0)
                .enqueue(new Callback<Result<User_Article>>() {
                    @Override
                    public void onResponse(Call<Result<User_Article>> call, Response<Result<User_Article>> response) {
                        Result<User_Article> result = response.body();




                    }

                    @Override
                    public void onFailure(Call<Result<User_Article>> call, Throwable t) {

                    }
                });
    }
}
