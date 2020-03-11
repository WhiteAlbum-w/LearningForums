package com.xiayang.learningforums;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class LoginActivity extends AppCompatActivity {

    private Toolbar toolbarLogin;
    private TextView idTv, landingTv, integralTv, rankingTv, rankingListTv, articleTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        toolbarLogin = findViewById(R.id.login_toolbar);
        toolbarLogin.setTitle("登录");
        setSupportActionBar(toolbarLogin);
        idTv = findViewById(R.id.login_id);
        landingTv = findViewById(R.id.login_landing);
        integralTv = findViewById(R.id.login_integral);
        rankingTv = findViewById(R.id.login_ranking);
        rankingListTv = findViewById(R.id.login_rankinglist);
        articleTv = findViewById(R.id.login_my_article);
    }
}
