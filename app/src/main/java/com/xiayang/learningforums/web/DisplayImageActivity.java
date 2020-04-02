package com.xiayang.learningforums.web;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.xiayang.learningforums.R;

import java.util.ArrayList;
import java.util.List;

public class DisplayImageActivity extends AppCompatActivity {
    private ViewPager viewPager;
    // view 的集合
    List<ImageView> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_image);
        viewPager = findViewById(R.id.image_viewPager);
        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);
        String[] imgs = intent.getStringArrayExtra("imgs");

        datas = new ArrayList<>();
        if (imgs != null) {
            for (int i = 0; i < imgs.length; i++) {
                ImageView iv = new ImageView(this);
                Glide.with(this).load(imgs[i]).into(iv);
                iv.setScaleType(ImageView.ScaleType.CENTER);
                datas.add(iv);
            }
        }

        // 设置适配器
        DisplayAdapter adapter = new DisplayAdapter(datas);
        viewPager.setAdapter(adapter);

        viewPager.setCurrentItem(position);
    }
}
