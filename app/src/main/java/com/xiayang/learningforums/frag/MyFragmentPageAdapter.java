package com.xiayang.learningforums.frag;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.xiayang.learningforums.R;

import java.util.List;

/**
 * @author xiayang1024 wfy19961024@gmail.com
 */
public class MyFragmentPageAdapter extends FragmentPagerAdapter {

    private Context context;
    private List<Fragment> fragmentList;

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
            return context.getString(R.string.tab_home);
        } else if(position == 1) {
            return context.getString(R.string.tab_project);
        } else if (position == 2) {
            return context.getString(R.string.tab_square);
        } else {
            return context.getString(R.string.tab_question);
        }
    }
}
