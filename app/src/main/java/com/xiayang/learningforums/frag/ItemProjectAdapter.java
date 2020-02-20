package com.xiayang.learningforums.frag;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.xiayang.learningforums.R;
import com.xiayang.learningforums.bean.Article;

import java.util.List;

/**
 * @author Aaron aaronzzxup@gmail.com
 */
public class ItemProjectAdapter extends RecyclerView.Adapter<ItemProjectAdapter.ProjectViewHolder> {

    private Context context;
    List<Article> datas;

    public ItemProjectAdapter(Context context, List<Article> datas) {
        this.context = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_item_project, parent,false);
        return new ProjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {
        Article article = datas.get(position);
        Glide.with(context).load(article.envelopePic).into(holder.iv);
        holder.tvTitle.setText(article.title);
        holder.tvDesc.setText(article.desc);
        holder.tvAuthor.setText(article.author);
        holder.tvClassify.setText(article.superChapterName);
        holder.tvTime.setText(article.niceDate);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class ProjectViewHolder extends RecyclerView.ViewHolder {

        ImageView iv;
        TextView tvTitle,tvDesc,tvAuthor,tvClassify,tvTime;
        public ProjectViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.item_project_iv);
            tvTitle = itemView.findViewById(R.id.item_project_title);
            tvDesc = itemView.findViewById(R.id.item_project_desc);
            tvAuthor = itemView.findViewById(R.id.item_project_author);
            tvClassify = itemView.findViewById(R.id.item_project_classify);
            tvTime = itemView.findViewById(R.id.item_project_time);
        }
    }
}
