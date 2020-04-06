package com.xiayang.learningforums.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.xiayang.learningforums.R;
import com.xiayang.learningforums.bean.TreeData;
import com.xiayang.learningforums.frag.TreeDataFragment;
import com.xiayang.learningforums.frag.TreePageAdapter;

import java.util.ArrayList;
import java.util.List;

public class TreeActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView tvTitle;
    private TabLayout tab;
    private ViewPager vp;

    private List<Fragment> fragmentList = new ArrayList<>(); // ViewPager 显示fragment的集合
    private List<TreeData> titleList = new ArrayList<>(); // title 集合
    private List<String> titles = new ArrayList<>();
    private TreePageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree);

        initView(); // 初始化
        initPager();
    }

    private void initPager() {
        vp = findViewById(R.id.tree_info_vp);
        for (TreeData data : titleList) {
            String name = data.name;
            titles.add(name);
            TreeDataFragment fragment = new TreeDataFragment();
            fragmentList.add(fragment);
        }
        // 创建 viewpager 的适配器
        adapter = new TreePageAdapter(getSupportFragmentManager(), fragmentList, titles);
        // 设置适配器
        vp.setAdapter(adapter);
        // 关联 tab 和 viewpager
        tab.setupWithViewPager(vp);
    }

    private void initView() {
        toolbar = findViewById(R.id.tree_info_toolbar);
        tvTitle = findViewById(R.id.tree_info_title);
        tab = findViewById(R.id.tree_info_tab);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        // 在 toolbar 上显示返回按钮
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // 返回按钮 finish处理
        finish();
        return true;
    }
}
