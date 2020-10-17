package com.xiayang.learningforums.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.xiayang.learningforums.R;
import com.xiayang.learningforums.frag.CollectFragment;
import com.xiayang.learningforums.frag.HomeFragment;
import com.xiayang.learningforums.frag.NavigationFragment;
import com.xiayang.learningforums.frag.SystemFragment;

public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        Intent intent = getIntent();
        if (intent != null) {
            int id = intent.getIntExtra("data", -1);
            if (id == R.id.nav_home) {
                replaceFragment(new HomeFragment());
            } else if (id == R.id.nav_navigation) {
                replaceFragment(new NavigationFragment());
            } else if (id == R.id.nav_system) {
                replaceFragment(new SystemFragment());
            }
        }
    }

    public static void start(Context context, int id) {
        Intent intent = new Intent(context, NewsActivity.class);
        intent.putExtra("data",id);
        context.startActivity(intent);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.home, fragment);
        transaction.commit();
    }
}
