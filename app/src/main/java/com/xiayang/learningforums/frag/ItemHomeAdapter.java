package com.xiayang.learningforums.frag;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
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
 * @author Aaron aaronzzxup@gmail.com
 */
public class ItemHomeAdapter extends RecyclerView.Adapter<ItemHomeAdapter.HomePageViewHolder> {

    private Context context;
    private List<Article> datas;
    private OnItemClickListener onItemClickListener;
    private Article article;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void OnItemClick(View view, int position);
    }

    ItemHomeAdapter(Context context, List<Article> datas) {
        this.context = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public HomePageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.recycler_item_home_page, parent, false);
        HomePageViewHolder holder = new HomePageViewHolder(itemView);
        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull HomePageViewHolder holder, int position) {
        article = datas.get(position);
        holder.tvTitle.setText(article.title);
        if (TextUtils.isEmpty(article.author)) {
            // Context 的 getString 方法有 2 个参数的重载，查下它的用法，
            // 可以解决类中字符串硬编码的问题。
            holder.tvAuthor.setText(context.getString(R.string.author, article.shareUser));
        } else {
            holder.tvAuthor.setText(context.getString(R.string.author, article.author));
        }
        holder.tvClassify.setText(context.getString(R.string.classify, article.superChapterName));
        holder.tvTime.setText(article.niceShareDate);
        // 点击事件监听
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                Article art = datas.get(adapterPosition);
                Intent intent = new Intent(context, NetWebActivity.class);
                intent.putExtra("data", art.link);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    static class HomePageViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle,tvAuthor,tvClassify,tvTime;

        HomePageViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.item_home_page_title);
            tvAuthor = itemView.findViewById(R.id.item_home_page_author);
            tvClassify = itemView.findViewById(R.id.item_home_page_classify);
            tvTime = itemView.findViewById(R.id.item_home_page_time);
        }
    }
}
