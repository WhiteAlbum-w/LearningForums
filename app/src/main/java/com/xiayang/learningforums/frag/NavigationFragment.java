package com.xiayang.learningforums.frag;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.xiayang.learningforums.NewsActivity;
import com.xiayang.learningforums.R;
import com.xiayang.learningforums.bean.Article;
import com.xiayang.learningforums.bean.NavigationData;
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
public class NavigationFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerNav; // 缩写应该使用约定俗成得缩写
    private Toolbar toolbarNav; // 缩写应该使用约定俗成得缩写
    private NavigationView navNav;
    private RecyclerView rvLeftNav;
    private RecyclerView rvRightNav;
    private ItemLeftNavigationAdapter leftNavigationAdapter;
    private RecyclerView.Adapter rightNavAdapter;

    private List<NavigationData> sourceList = new ArrayList<>();
    private List<String> leftNavDataList = new ArrayList<>();
    private List<Article> rightNavDataList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navigation, container, false);
        // 设置 Toolbar
        setHasOptionsMenu(true);
        toolbarNav = view.findViewById(R.id.navigation_toolbar);
        drawerNav = view.findViewById(R.id.navigation_drawer);
        navNav = view.findViewById(R.id.navigation_navigation);
        rvLeftNav = view.findViewById(R.id.navigation_left_rv);
        rvRightNav = view.findViewById(R.id.navigation_right_rv);
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        if (appCompatActivity != null) {
            appCompatActivity.setSupportActionBar(toolbarNav);
            ActionBar actionBar = appCompatActivity.getSupportActionBar();
            if (actionBar != null) {
                actionBar.setTitle("Navigation");
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setHomeAsUpIndicator(R.drawable.ic_main_home_black_24dp);
            }
        }
        navNav.setNavigationItemSelectedListener(this);

        initLeftNav();
        initRightNav();

        request();

        return view;
    }

    private void initLeftNav() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvLeftNav.setLayoutManager(layoutManager);
        leftNavigationAdapter = new ItemLeftNavigationAdapter(getContext(), leftNavDataList);
        leftNavigationAdapter.setOnItemClickListener(new ItemLeftNavigationAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View itemView, int position) {
                leftNavigationAdapter.setThisPosition(position);
                leftNavigationAdapter.notifyDataSetChanged();
                String name = leftNavDataList.get(position);
                for (NavigationData data : sourceList) {
                    if (name.equals(data.name)) {
                        rightNavDataList.clear();
                        rightNavDataList.addAll(data.articles);
                        rightNavAdapter.notifyDataSetChanged();
                        return;
                    }
                }
            }
        });
        rvLeftNav.setAdapter(leftNavigationAdapter);
    }

    private void initRightNav() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvRightNav.setLayoutManager(layoutManager);
        rightNavAdapter = new ItemRightNavigationAdapter(rightNavDataList);
        rvRightNav.setAdapter(rightNavAdapter);
    }

    private void request() {
        NetworkManager.getInstance()
                .getNavigationService()
                .getNavigationDatas()
                .enqueue(new Callback<Result<List<NavigationData>>>() {
                    @Override
                    public void onResponse(Call<Result<List<NavigationData>>> call, Response<Result<List<NavigationData>>> response) {
                        Result<List<NavigationData>> result = response.body();
                        if (result != null) {
                            sourceList.clear();
                            sourceList.addAll(result.data);
                            leftNavDataList.clear();
                            for (NavigationData navi : result.data) {
                                leftNavDataList.add(navi.name);
                            }
                            leftNavigationAdapter.notifyDataSetChanged();
                            if (!result.data.isEmpty()) {
                                rightNavDataList.clear();
                                rightNavDataList.addAll(result.data.get(0).articles);
                                rightNavAdapter.notifyDataSetChanged();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Result<List<NavigationData>>> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerNav.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                // 这里是二级页面，应该是回首页
                // Intent intent = new Intent(getActivity(), MainActivity.class);
                // startActivity(intent);
                if (getActivity() != null) getActivity().finish();
                break;
            // 以下写法也不太行，这样会导致 NewsActivity 启动很多次，也就是有很多个实例
            // 正确做法是 NewsActivity 写个方法供 Fragment 调用切换 Fragment
            // 这个留给你发挥
            case R.id.nav_system:
                if (getActivity() != null) getActivity().finish();
                NewsActivity.start(getActivity(), R.id.nav_system);
                break;
            case R.id.nav_collect:
                NewsActivity.start(getActivity(), R.id.nav_collect);
                break;
            case R.id.nav_chat:
                NewsActivity.start(getActivity(), R.id.nav_chat);
                break;
            case R.id.nav_classification:
                NewsActivity.start(getActivity(), R.id.nav_classification);
                break;
            default:
                break;
        }
        drawerNav.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        navNav.setCheckedItem(R.id.nav_navigation);
    }
}
