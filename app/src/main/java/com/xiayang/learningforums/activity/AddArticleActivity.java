package com.xiayang.learningforums.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.xiayang.learningforums.R;
import com.xiayang.learningforums.utils.StatusBarUtils;

public class AddArticleActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_article);
//
//        StatusBarUtils.setColor(this,getResources().getColor(R.color.colorPrimary));
//
//        initView();
    }

//    private void initView() {
//        toolbar = findViewById(R.id.add_toolbar);
//        toolbar.setTitle("");
//        setSupportActionBar(toolbar);
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setHomeButtonEnabled(true);
//        }
//    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        finish();
//        return true;
//    }
}
