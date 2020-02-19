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
import com.xiayang.learningforums.bean.homepage.Article;

import java.util.List;

/**
 * @author Aaron aaronzzxup@gmail.com
 */
public class ItemHomeAdapter extends RecyclerView.Adapter<ItemHomeAdapter.HomePageViewHolder> {

    private Context context;
    List<Article> datas;

    ItemHomeAdapter(Context context, List<Article> datas) {
        this.context = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public HomePageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_page, parent,false);
        HomePageViewHolder holder = new HomePageViewHolder(view);
        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull HomePageViewHolder holder, int position) {
        Article article = datas.get(position);
        holder.tvTitle.setText(article.title);
        if (article.author == null) {
            holder.tvAuthor.setText("作者:" + article.shareUser);
        } else {
            holder.tvAuthor.setText("作者:" + article.author);
        }
        holder.tvClassify.setText("分类:" + article.superChapterName);
        holder.tvTime.setText(article.niceShareDate);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class HomePageViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle,tvAuthor,tvClassify,tvTime;
        public HomePageViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.item_home_page_title);
            tvAuthor = itemView.findViewById(R.id.item_home_page_author);
            tvClassify = itemView.findViewById(R.id.item_home_page_classify);
            tvTime = itemView.findViewById(R.id.item_home_page_time);
        }
    }
}
