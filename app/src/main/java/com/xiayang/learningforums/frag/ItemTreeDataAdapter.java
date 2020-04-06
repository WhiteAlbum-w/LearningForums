package com.xiayang.learningforums.frag;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xiayang.learningforums.R;
import com.xiayang.learningforums.bean.Article;
import com.xiayang.learningforums.web.NetWebActivity;

import java.util.List;

/**
 * desc   : 体系数据信息的列表
 * <p>
 * author : Alexander
 * e-mail : wfy19961024@gmail.com
 * date   : 2020/4/6
 */
public class ItemTreeDataAdapter extends RecyclerView.Adapter<ItemTreeDataAdapter.TreeDataViewHolder> {

    private Context context;
    private List<Article> datas;

    public ItemTreeDataAdapter(Context context, List<Article> datas) {
        this.context = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public TreeDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.recycler_item_tree_data, parent, false);
        TreeDataViewHolder holder = new TreeDataViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TreeDataViewHolder holder, int position) {
        Article data = datas.get(position);

        holder.tvTitle.setText(data.title);
        holder.tvAuthor.setText(data.shareUser);
        holder.tvTime.setText(data.niceShareDate);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                Article article = datas.get(adapterPosition);
                Intent intent = new Intent(context, NetWebActivity.class);
                intent.putExtra("data", article.link);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    static class TreeDataViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle, tvAuthor, tvTime;

        public TreeDataViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.item_tree_data_title);
            tvAuthor = itemView.findViewById(R.id.item_tree_data_author);
            tvTime = itemView.findViewById(R.id.item_tree_data_time);
        }
    }
}
