package com.xiayang.learningforums.frag;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.xiayang.learningforums.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollectFragment extends Fragment {
    private Toolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View itemView = inflater.inflate(R.layout.fragment_collect, container, false);
        toolbar = itemView.findViewById(R.id.setting_toolbar);
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        if (appCompatActivity != null) {
            toolbar.setTitle("");
            appCompatActivity.setSupportActionBar(toolbar);
            ActionBar actionBar = appCompatActivity.getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setHomeButtonEnabled(true);
            }
        }
        setHasOptionsMenu(true);

        return itemView;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (getActivity() != null) {
            getActivity().finish();
        }
        return true;
    }
}
