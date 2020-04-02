package com.xiayang.learningforums.frag;

import android.content.Context;
import android.content.Intent;
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
import com.xiayang.learningforums.web.NetWebActivity;

import java.util.List;

/**
 * @author Aaron aaronzzxup@gmail.com
 */
public class ItemProjectAdapter extends RecyclerView.Adapter<ItemProjectAdapter.ProjectViewHolder> {

    private Context context;
    private List<Article> datas;

    public ItemProjectAdapter(Context context, List<Article> datas) {
        this.context = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_item_project, parent, false);
        return new ProjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {
        Article article = datas.get(position);
        // 像这种连续调用方法的，记得换行
        Glide.with(context)
                .load(article.envelopePic)
                .into(holder.iv);
        holder.tvTitle.setText(article.title);
        holder.tvDesc.setText(article.desc);
        holder.tvAuthor.setText(article.author);
        holder.tvClassify.setText(article.superChapterName);
        holder.tvTime.setText(article.niceDate);

        // 点击事件的监听
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

    static class ProjectViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv;
        private TextView tvTitle, tvDesc, tvAuthor, tvClassify, tvTime;

        ProjectViewHolder(@NonNull View itemView) {
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
