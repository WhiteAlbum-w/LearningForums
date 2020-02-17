package com.xiayang.learningforums;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayoutMain;
    NavigationView navMain;
    Toolbar toolbarMain;
    TabLayout tabLayoutMain;
    ViewPager vPMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();  //初始化控件函数
    }

    //  初始化控件函数
    private void initView() {
        toolbarMain = findViewById(R.id.main_bar);
        toolbarMain.setTitle("");
        setSupportActionBar(toolbarMain);
        tabLayoutMain = findViewById(R.id.tab_layout);
        vPMain = findViewById(R.id.view_pager);
        drawerLayoutMain = findViewById(R.id.drawer_layout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_classification_black_24dp);
        }
        navMain = findViewById(R.id.navigation_view);
    }

}
