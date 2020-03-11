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
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.xiayang.learningforums.R;
import com.xiayang.learningforums.bean.Article;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment {

    private DrawerLayout drawerChat;
    private NavigationView navigationChat;
    private Toolbar toolbarChat;
    private TabLayout tabChat;
    private ViewPager vpChat;
    private List<Fragment> fragmentList = new ArrayList<>(); // ViewPager 所显示的内容
    private List<Article.Tags> tags;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View itemView = inflater.inflate(R.layout.fragment_chat, container, false);

        toolbarChat = itemView.findViewById(R.id.chat_toolbar);
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        if (appCompatActivity != null) {
            appCompatActivity.setSupportActionBar(toolbarChat);
            ActionBar actionBar = appCompatActivity.getSupportActionBar();
            if (actionBar != null) {
                actionBar.setTitle("Chat");
                actionBar.setHomeAsUpIndicator(R.drawable.ic_main_home_black_24dp);
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
        }
        setHasOptionsMenu(true);

        drawerChat = itemView.findViewById(R.id.chat_drawer);
        navigationChat = itemView.findViewById(R.id.chat_navigation);
        tabChat = itemView.findViewById(R.id.chat_tab);
        vpChat = itemView.findViewById(R.id.chat_vp);

        initViewPager(); //初始化页面
        return itemView;
    }

    private void initViewPager() {

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerChat.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }
}
