package com.xiayang.learningforums.frag;

import android.content.Intent;
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
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.xiayang.learningforums.MainActivity;
import com.xiayang.learningforums.NewsActivity;
import com.xiayang.learningforums.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drNav;
    private Toolbar barNav;
    private NavigationView navNav;
    private RecyclerView rvLeftNav;
    private RecyclerView rvRightNav;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navigation, container, false);
        // 设置 Toolbar
        setHasOptionsMenu(true);
        barNav = view.findViewById(R.id.navigation_toolbar);
        drNav = view.findViewById(R.id.navigation_drawer);
        navNav = view.findViewById(R.id.navigation_navigation);
        rvLeftNav = view.findViewById(R.id.navigation_left_rv);
        rvRightNav = view.findViewById(R.id.navigation_right_rv);
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        if (appCompatActivity != null) {
            appCompatActivity.setSupportActionBar(barNav);
            ActionBar actionBar = appCompatActivity.getSupportActionBar();
            if (actionBar != null) {
                actionBar.setTitle("Navigation");
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setHomeAsUpIndicator(R.drawable.ic_main_home_black_24dp);
            }
        }
        navNav.setNavigationItemSelectedListener(this);
        navNav.setCheckedItem(R.id.nav_navigation);

        // 创建 RecyclerView 适配器
        return view;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drNav.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_system:
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
        drNav.closeDrawer(GravityCompat.START);
        return true;
    }
}
