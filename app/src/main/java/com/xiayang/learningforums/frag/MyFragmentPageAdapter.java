package com.xiayang.learningforums.frag;

import android.content.Context;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @author Aaron aaronzzxup@gmail.com
 */
public class MyFragmentPageAdapter extends FragmentPagerAdapter {

    Context context;
    List<Fragment> fragmentList;

    public MyFragmentPageAdapter(@NonNull FragmentManager fm, Context context,List<Fragment> fragmentList) {
        super(fm);
        this.context = context;
        this.fragmentList = fragmentList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        //  返回指定位置得fragment
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        //  返回fragment 集合得长度
        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "主页";
        } else if(position == 1) {
            return "项目";
        } else if (position == 2) {
            return "广场";
        } else {
            return "问答";
        }
    }
}
