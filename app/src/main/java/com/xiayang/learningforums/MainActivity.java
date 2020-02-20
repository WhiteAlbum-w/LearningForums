package com.xiayang.learningforums;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.xiayang.learningforums.frag.HomeFragment;
import com.xiayang.learningforums.frag.MyFragmentPageAdapter;

import com.xiayang.learningforums.frag.ProjectFragment;
import com.xiayang.learningforums.frag.QuestionFragment;
import com.xiayang.learningforums.frag.SquareFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayoutMain;
    private TabLayout tabLayoutMain;
    private ViewPager vpMain;
    private List<Fragment> fragmentList; //显示 ViewPager 内容得集合
    private NavigationView navigationMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView(); //初始化控件函数
    }

    //  初始化控件函数
    private void initView() {
        Toolbar toolbarMain = findViewById(R.id.main_bar);
        toolbarMain.setTitle("");
        setSupportActionBar(toolbarMain);
        tabLayoutMain = findViewById(R.id.tab_layout);
        fragmentList = new ArrayList<>();  //初始化fragment
        fragmentList.add(new HomeFragment());
        fragmentList.add(new ProjectFragment());
        fragmentList.add(new SquareFragment());
        fragmentList.add(new QuestionFragment());
        vpMain = findViewById(R.id.view_pager);
        //        ViewPager 得适配器
        MyFragmentPageAdapter fragmentPageAdapter =
                new MyFragmentPageAdapter(getSupportFragmentManager(), this, fragmentList);
        //  设置适配器
        vpMain.setAdapter(fragmentPageAdapter);
        //  tab 关联 ViewPager
        tabLayoutMain.setupWithViewPager(vpMain);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_main_home_black_24dp);
        }
        drawerLayoutMain = findViewById(R.id.drawer_layout);
        navigationMain = findViewById(R.id.navigation_view);
        navigationMain.setCheckedItem(R.id.nav_home);
        //  导航栏跳转得方法
        navigationMain.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                drawerLayoutMain.closeDrawer(GravityCompat.START);
//                NewsActivity.start(MainActivity.this, R.id.nav_home);
                break;
            case R.id.nav_navigation:
                NewsActivity.start(MainActivity.this, R.id.nav_navigation);
                break;
            case R.id.nav_system:
                NewsActivity.start(MainActivity.this, R.id.nav_system);
                break;
            case R.id.nav_collect:
                NewsActivity.start(MainActivity.this, R.id.nav_collect);
                break;
            case R.id.nav_chat:
                NewsActivity.start(MainActivity.this, R.id.nav_chat);
                break;
            case R.id.nav_classification:
                NewsActivity.start(MainActivity.this, R.id.nav_classification);
                break;
            default:
                break;
        }
        drawerLayoutMain.closeDrawer(GravityCompat.START);
        return true;
    }

    //    Toolbar 上面得控件点击事件
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {  //导航栏开关
            case android.R.id.home:
                drawerLayoutMain.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }
}
