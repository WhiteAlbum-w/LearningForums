package com.xiayang.learningforums.frag;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xiayang.learningforums.R;
import com.xiayang.learningforums.bean.Article;

import java.util.List;

/**
 * Date: 2020/2/23
 * Author: aaronzzxup@gmail.com
 */
final class ItemRightNavigationAdapter extends RecyclerView.Adapter<ItemRightNavigationAdapter.ViewHolder> {

    private List<Article> dataList;

    ItemRightNavigationAdapter(List<Article> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.recycler_item_right_navigation, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Article article = dataList.get(position);
        holder.tvNav.setText(article.title);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNav;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNav = itemView.findViewById(R.id.item_right_navigation_tv);
        }
    }
}
