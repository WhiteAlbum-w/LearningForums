package com.xiayang.learningforums.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.xiayang.learningforums.R;
import com.xiayang.learningforums.bean.Result;
import com.xiayang.learningforums.databinding.ActivityLoginBinding;
import com.xiayang.learningforums.network.NetworkManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityLoginBinding viewBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(viewBinding.getRoot());

        initView();

    }

    private void initView() {
        // 初始化控件的函数
        viewBinding.loginToolbar.setTitle("登录");
        setSupportActionBar(viewBinding.loginToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }

        viewBinding.loginLogin.setOnClickListener(this);
        viewBinding.loginRegistered.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_login: // 点击登录
                String username = viewBinding.loginUsername.getText().toString().trim();
                String password = viewBinding.loginPassword.getText().toString().trim();
                NetworkManager.getInstance()
                        .getLoginService()
                        .login(username, password)
                        .enqueue(new Callback<Result<Object>>() {
                            @Override
                            public void onResponse(Call<Result<Object>> call, Response<Result<Object>> response) {
                                Result<Object> result = response.body();
                                if (username.equals("")) {
                                    Toast.makeText(LoginActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                                } else if (password.equals("")) {
                                    Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                                } else if (result != null) {
                                    if (result.data != null) {
                                        SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                                        editor.putString("name", username);
                                        editor.putString("pwd", password);
                                        editor.apply();
                                        Log.d("dddd", "onResponse: 222" + response.body());
                                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                                        finish();
                                    } else {
                                        Log.d("dddd", "onResponse: 3333" + response.body());
                                        Toast.makeText(LoginActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<Result<Object>> call, Throwable t) {
                                t.printStackTrace();
                            }
                        });
                break;
            case R.id.login_registered: // 点击进去注册页面
                startActivity(new Intent(this, RegisterActivity.class));
                break;
        }
    }

    /**
     * Toolbar控件上按钮的点击事件监听
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
