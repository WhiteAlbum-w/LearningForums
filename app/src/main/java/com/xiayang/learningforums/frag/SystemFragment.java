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
import com.xiayang.learningforums.R;
import com.xiayang.learningforums.activity.NewsActivity;
import com.xiayang.learningforums.bean.Result;
import com.xiayang.learningforums.bean.TreeData;
import com.xiayang.learningforums.network.NetworkManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SystemFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerSystem;
    private Toolbar toolbarSystem;
    private NavigationView navigationSystem;
    private RecyclerView rvLeft;
    private RecyclerView rvRight;
    private ItemLeftSystemAdapter leftSystemAdapter;
    private ItemRightSystemAdapter rightSystemAdapter;

    private List<TreeData> sourceList = new ArrayList<>();
    private List<String> leftSystemDataList = new ArrayList<>();
    private List<TreeData> rightSystemDataList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_system, container, false);

        toolbarSystem = view.findViewById(R.id.system_toolbar);
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        if (appCompatActivity != null) {
            appCompatActivity.setSupportActionBar(toolbarSystem);
            ActionBar actionBar = appCompatActivity.getSupportActionBar();
            if (actionBar != null) {
                actionBar.setTitle("System");
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setHomeAsUpIndicator(R.drawable.ic_main_home_black_24dp);
            }
        }
        setHasOptionsMenu(true);

        rvLeft = view.findViewById(R.id.system_left_rv);
        rvRight = view.findViewById(R.id.system_right_rv);
        drawerSystem = view.findViewById(R.id.system_drawer);
        navigationSystem = view.findViewById(R.id.system_navigation);
        navigationSystem.setNavigationItemSelectedListener(this);

        initLeftSystem();
        initRightSystem();

        request();
        return view;
    }

    private void initLeftSystem() {
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rvLeft.setLayoutManager(layoutManager);
        leftSystemAdapter = new ItemLeftSystemAdapter(getContext(), leftSystemDataList);
        leftSystemAdapter.setOnItemClickListener((view, position) -> {
            leftSystemAdapter.setThisPosition(position);
            leftSystemAdapter.notifyDataSetChanged();
            String name = leftSystemDataList.get(position);
            for (TreeData data : sourceList) {
                if (name.equals(data.name)) {
                    rightSystemDataList.clear();
                    rightSystemDataList.addAll(data.children);
                    rightSystemAdapter.notifyDataSetChanged();
                    return;
                }
            }
        });
        rvLeft.setAdapter(leftSystemAdapter);
    }


    private void initRightSystem() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rvRight.setLayoutManager(layoutManager);
        rightSystemAdapter = new ItemRightSystemAdapter(getContext(), rightSystemDataList);
        rvRight.setAdapter(rightSystemAdapter);
    }

    private void request() {
        NetworkManager.getInstance()
                .getTreeService()
                .getTreeDatas()
                .enqueue(new Callback<Result<List<TreeData>>>() {
                    @Override
                    public void onResponse(Call<Result<List<TreeData>>> call, Response<Result<List<TreeData>>> response) {
                        Result<List<TreeData>> result = response.body();
                        if (result != null) {
                            sourceList.clear();
                            sourceList.addAll(result.data);
                            leftSystemDataList.clear();
                            for (TreeData tree : result.data) {
                                leftSystemDataList.add(tree.name);
                            }
                            leftSystemAdapter.notifyDataSetChanged();
                            if (!result.data.isEmpty()) {
                                rightSystemDataList.clear();
                                rightSystemDataList.addAll(result.data.get(0).children);
                                rightSystemAdapter.notifyDataSetChanged();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Result<List<TreeData>>> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                if (getActivity() != null) getActivity().finish();
                break;
            case R.id.nav_navigation:
                if (getActivity() != null) getActivity().finish();
                NewsActivity.start(getActivity(), R.id.nav_navigation);
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
        }
        drawerSystem.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerSystem.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        navigationSystem.setCheckedItem(R.id.nav_system);
    }
}
