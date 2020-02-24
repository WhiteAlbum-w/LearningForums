package com.xiayang.learningforums.frag;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xiayang.learningforums.R;
import com.xiayang.learningforums.bean.TreeData;

import java.util.List;

/**
 * @author xiayang1024 wfy19961024@gmail.com
 */
public class ItemRightSystemAdapter extends RecyclerView.Adapter<ItemRightSystemAdapter.RightSystemViewHolder> {

    private Context context;
    List<TreeData> dataList;

    public ItemRightSystemAdapter(Context context, List<TreeData> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public RightSystemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.recycler_item_right_system, parent, false);
        RightSystemViewHolder holder = new RightSystemViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RightSystemViewHolder holder, int position) {
        TreeData data = dataList.get(position);
        holder.tvTitle.setText(data.name);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class RightSystemViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;

        public RightSystemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.item_right_system_name);
        }
    }
}
