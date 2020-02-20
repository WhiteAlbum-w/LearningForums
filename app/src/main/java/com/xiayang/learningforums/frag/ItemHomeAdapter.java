package com.xiayang.learningforums.frag;

import android.annotation.SuppressLint;
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
 * @author Aaron aaronzzxup@gmail.com
 */
public class ItemHomeAdapter extends RecyclerView.Adapter<ItemHomeAdapter.HomePageViewHolder> {

    private Context context;
    private List<Article> datas;

    ItemHomeAdapter(Context context, List<Article> datas) {
        this.context = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public HomePageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_item_home_page, parent, false);
        return new HomePageViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull HomePageViewHolder holder, int position) {
        Article article = datas.get(position);
        holder.tvTitle.setText(article.title);
        if (article.author == null) {
            // Context 的 getString 方法有 2 个参数的重载，查下它的用法，
            // 可以解决类中字符串硬编码的问题。
            holder.tvAuthor.setText(context.getString(R.string.author) + article.shareUser);
        } else {
            holder.tvAuthor.setText(context.getString(R.string.author) + article.author);
        }
        holder.tvClassify.setText(context.getString(R.string.classify) + article.superChapterName);
        holder.tvTime.setText(article.niceShareDate);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class HomePageViewHolder extends RecyclerView.ViewHolder {
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
