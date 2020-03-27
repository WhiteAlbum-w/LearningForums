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
 * author : Alexander
 * e-mail : wfy19961024@gmail.com
 * date   : 2020/3/25
 * desc   : 广场 Recycler 的 Adapter
 */
public class ItemSquareAdapter extends RecyclerView.Adapter<ItemSquareAdapter.SquareViewHolder> {

    private Context context;
    private List<Article> datas;

    public ItemSquareAdapter(Context context, List<Article> datas) {
        this.context = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public SquareViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.recycler_item_square, parent, false);
        SquareViewHolder holder = new SquareViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SquareViewHolder holder, int position) {
        Article datas = this.datas.get(position);
        holder.tvTitle.setText(datas.title);
        holder.tvAuthor.setText(datas.shareUser);
        holder.tvTime.setText(datas.niceDate);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    static class SquareViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle,tvAuthor,tvTime;

        public SquareViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.item_square_title);
            tvAuthor = itemView.findViewById(R.id.item_square_author);
            tvTime = itemView.findViewById(R.id.item_square_time);
        }
    }

}
