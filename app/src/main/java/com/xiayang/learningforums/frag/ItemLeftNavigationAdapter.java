package com.xiayang.learningforums.frag;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xiayang.learningforums.R;

/**
 * @author Aaron aaronzzxup@gmail.com
 */
public class ItemLeftNavigationAdapter extends RecyclerView.Adapter<ItemLeftNavigationAdapter.LeftNavigationViewHolder> {

    @NonNull
    @Override
    public LeftNavigationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull LeftNavigationViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class LeftNavigationViewHolder extends RecyclerView.ViewHolder {

        private TextView tvNav;

        public LeftNavigationViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNav = itemView.findViewById(R.id.item_left_navigation_title);
        }
    }
}
