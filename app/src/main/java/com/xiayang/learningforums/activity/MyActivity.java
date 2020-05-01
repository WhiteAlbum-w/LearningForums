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
import androidx.annotation.Nullable;
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
    private String resultData;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        viewBinding = ActivityMyBinding.inflate(getLayoutInflater());
        setContentView(viewBinding.getRoot());
        // 沉浸状态栏
        StatusBarUtils.setColor(this,getResources().getColor(R.color.colorPrimary));
        // 初始化
        initView();

        Intent intent = new Intent();
        username = intent.getStringExtra("username");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        resultData = data.getStringExtra("username");
                        viewBinding.myLanding.setText(resultData);
                    }
                }
                break;
            default:
        }
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
                if (!TextUtils.isEmpty(resultData) || !TextUtils.isEmpty(name)) {
                    Toast.makeText(this, "已登录", Toast.LENGTH_SHORT).show();
                } else {
                    startActivityForResult(new Intent(this, LoginActivity.class), 1);
                }
                break;
            case R.id.my_rankinglist:
                startActivity(new Intent(this, RankActivity.class));
                break;
            case R.id.my_article:
                if (!TextUtils.isEmpty(resultData) || !TextUtils.isEmpty(name)) {
                    startActivity(new Intent(this, MyArticleActivity.class));
                } else {
                    startActivityForResult(new Intent(this, LoginActivity.class), 1);
                }
                break;
            case R.id.my_setting:
                startActivity(new Intent(this, SettingActivity.class));
                break;
        }
    }
}
