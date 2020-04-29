package com.xiayang.learningforums.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.xiayang.learningforums.R;
import com.xiayang.learningforums.databinding.ActivityMyBinding;
import com.xiayang.learningforums.utils.SPUtil;
import com.xiayang.learningforums.utils.StatusBarUtils;

public class MyActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMyBinding viewBinding;
    private SharedPreferences perf;
    private TextView tvRank, tvArt, tvSetting;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        viewBinding = ActivityMyBinding.inflate(getLayoutInflater());
        setContentView(viewBinding.getRoot());

        StatusBarUtils.setColor(this,getResources().getColor(R.color.colorPrimary));

        initView();
    }

    private void initView() {
        // Toolbar 的设置，显示返回按钮的方法
        viewBinding.loginToolbar.setTitle("我的");
        setSupportActionBar(viewBinding.loginToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }

        viewBinding.myLanding.setOnClickListener(this);
        viewBinding.myRankinglist.setOnClickListener(this);
        viewBinding.myArticle.setOnClickListener(this);
        viewBinding.mySetting.setOnClickListener(this);
//        perf = getSharedPreferences("data", MODE_PRIVATE);
//        name = perf.getString("name", "");
        name = (String) SPUtil.getInstance()
                .get(this, "name", "");
        if (!TextUtils.isEmpty(name)) {
            viewBinding.myLanding.setText(name);
        } else {
            viewBinding.myLanding.setText("请登录");
        }

        tvArt = findViewById(R.id.my_article);
        tvRank = findViewById(R.id.my_ranking);
        tvSetting = findViewById(R.id.my_setting);
    }

    /**
     * Toolbar 点击事件监听
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 布局控件点击事件的方法
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_landing: // 点击进入登录界面
                // String name = getIntent().getStringExtra("name");

                if (!TextUtils.isEmpty(name)) {
                    Toast.makeText(this, "已登录", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(MyActivity.this, LoginActivity.class));
                }
                break;
            case R.id.my_rankinglist:
                startActivity(new Intent(this, RankActivity.class));
                break;
            case R.id.my_article:
//                Object name = SPUtil.getInstance()
//                        .get(MyActivity.this, "name", true);
//                Log.d("NAME", "onClick: name" + name);
                if (!TextUtils.isEmpty(name)) {
                    startActivity(new Intent(this, MyArticleActivity.class));
                } else {
                    startActivity(new Intent(this, LoginActivity.class));
                }
                break;
            case R.id.my_setting:
                startActivity(new Intent(this, SettingActivity.class));
                break;
        }
    }
}
