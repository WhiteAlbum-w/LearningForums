package com.xiayang.learningforums.frag;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * desc   :
 * <p>
 * author : Alexander
 * e-mail : wfy19961024@gmail.com
 * date   : 2020/4/6
 */
public class TreePageAdapter extends FragmentStatePagerAdapter {
    private Context context;
    private List<Fragment> fragmentList;
    private List<String> titles;

    public TreePageAdapter(@NonNull FragmentManager fm, List<Fragment> fragmentList, List<String> titles) {
        super(fm);
        this.fragmentList = fragmentList;
        this.titles = titles;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        // 返回置顶位置的标题
        String title = titles.get(position);
        return title;
    }
}
