package com.xiayang.learningforums.frag;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xiayang.learningforums.R;

import java.util.List;

/**
 * @author Aaron aaronzzxup@gmail.com
 */
public class ItemLeftSystemAdapter extends RecyclerView.Adapter<ItemLeftSystemAdapter.LeftSystemViewHolder> {

    private Context context;
    List<String> dataList;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        public void OnItemClick(View view, int position);
    }

    public ItemLeftSystemAdapter(Context context, List<String> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public LeftSystemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.recycler_item_left_system, parent, false);
        LeftSystemViewHolder holder = new LeftSystemViewHolder(itemView);
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                int position = holder.getAdapterPosition();
                onItemClickListener.OnItemClick(v, position);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull LeftSystemViewHolder holder, int position) {
        holder.tvTitle.setText(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class LeftSystemViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;

        public LeftSystemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.item_left_system_title);
        }
    }
}
