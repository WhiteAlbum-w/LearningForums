package com.xiayang.learningforums.frag;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xiayang.learningforums.R;

import java.util.List;

public class ItemLeftNavigationAdapter extends RecyclerView.Adapter<ItemLeftNavigationAdapter.LeftNavigationViewHolder> {

    private Context context;
    private List<String> dataList;
    private OnItemClickListener onItemClickListener;
    private int thisPosition;

    private int getThisPosition() {
        return thisPosition;
    }

    void setThisPosition(int thisPosition) {
        this.thisPosition = thisPosition;
    }

    void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    interface OnItemClickListener {
        void OnItemClick(View itemView, int position);
    }

    ItemLeftNavigationAdapter(Context context, List<String> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public LeftNavigationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.recycler_item_left_navigation, parent, false);
        LeftNavigationViewHolder holder = new LeftNavigationViewHolder(itemView);
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                int position = holder.getAdapterPosition();
                onItemClickListener.OnItemClick(v, position);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull LeftNavigationViewHolder holder, int position) {
        holder.tvNav.setText(dataList.get(position));
        if (position == getThisPosition()) {
            holder.tvNav.setBackgroundColor(Color.BLUE);
        } else {
            holder.tvNav.setBackgroundColor(Color.WHITE);
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    /**
     * 内部类最好使用静态内部类，因为普通嵌套类会隐式持有外部类引用，容易造成内存泄漏
     */
    static class LeftNavigationViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNav;

        LeftNavigationViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNav = itemView.findViewById(R.id.item_left_navigation_title);
        }
    }
}
