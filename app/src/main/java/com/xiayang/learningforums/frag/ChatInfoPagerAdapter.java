package com.xiayang.learningforums.frag;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * @author xiayang1024 wfy19961024@gmail.com
 */
public class ChatInfoPagerAdapter extends FragmentStatePagerAdapter {

    private Context context;
    private List<Fragment> fragmentList;

    public ChatInfoPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
