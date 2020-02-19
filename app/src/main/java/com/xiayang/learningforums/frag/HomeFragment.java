package com.xiayang.learningforums.frag;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
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
        rvPage = view.findViewById(R.id.home_page_rv);

        datas = new ArrayList<>();
//        创建 Recycler 得适配器
        ItemHomeAdapter adapter = new ItemHomeAdapter(getContext(), datas);
//        设置适配器
        rvPage.setAdapter(adapter);
//        设置布局管理器
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
                        ArticleList articleList = article.data;
                        List<Article> list = articleList.datas;
                        datas.clear();
                        datas.addAll(list);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<Result<ArticleList>> call, Throwable t) {
                        Toast.makeText(getContext(),"网络加载失败",Toast.LENGTH_SHORT).show();
                    }
                });
        return view;
    }
}