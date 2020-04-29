package com.xiayang.learningforums.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.xiayang.learningforums.R;
import com.xiayang.learningforums.bean.Result;
import com.xiayang.learningforums.databinding.ActivityRegisterBinding;
import com.xiayang.learningforums.network.NetworkManager;
import com.xiayang.learningforums.utils.StatusBarUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityRegisterBinding viewBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(viewBinding.getRoot());

        StatusBarUtils.setColor(this,getResources().getColor(R.color.colorPrimary));

        initView();
    }

    private void initView() {
        viewBinding.registerToolbar.setTitle(R.string.Register);
        setSupportActionBar(viewBinding.registerToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        viewBinding.register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String username = viewBinding.registerUsername.getText().toString().trim();
        String password = viewBinding.registerPassword.getText().toString().trim();
        String repassword = viewBinding.registerRepassword.getText().toString().trim();
        NetworkManager.getInstance()
                .getLoginService()
                .register(username, password, repassword)
                .enqueue(new Callback<Result<Object>>() {
                    @Override
                    public void onResponse(Call<Result<Object>> call, Response<Result<Object>> response) {
                        Result<Object> result = response.body();
                        if (username.equals("")) {
                            Toast.makeText(RegisterActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                        } else if (password.equals("")) {
                            Toast.makeText(RegisterActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                        } else if (repassword.equals("")) {
                            Toast.makeText(RegisterActivity.this, "请输入确认密码", Toast.LENGTH_SHORT).show();
                        } else if (!password.equals(repassword)) {
                            Toast.makeText(RegisterActivity.this, "两次密码不一致", Toast.LENGTH_SHORT).show();
                        } else if (username.length() < 3) {
                            Toast.makeText(RegisterActivity.this, "用户名长度不能小于3", Toast.LENGTH_SHORT).show();
                        } else if (password.length() < 6) {
                            Toast.makeText(RegisterActivity.this, "密码长度不能小于6", Toast.LENGTH_SHORT).show();
                        } else if (result != null) {
                            if (result.data != null) {
                                Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(RegisterActivity.this, "注册失败，该用户名已被注册", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Result<Object>> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
