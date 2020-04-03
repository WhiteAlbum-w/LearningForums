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
 * desc   :
 * <p>
 * author : Alexander
 * e-mail : wfy19961024@gmail.com
 * date   : 2020/4/2
 */
public class ItemQuestionAdapter extends RecyclerView.Adapter<ItemQuestionAdapter.QuestionViewHolder> {
    private Context context;
    private List<Article> datas;

    public ItemQuestionAdapter(Context context, List<Article> datas) {
        this.context = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.recycler_item_question, parent, false);
        QuestionViewHolder holder = new QuestionViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        Article article = datas.get(position);
        holder.tvTitle.setText(article.title);
        holder.tvAuthor.setText(article.author);
        holder.tvTime.setText(article.niceShareDate);
        
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

    static class QuestionViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle,tvAuthor,tvTime;

        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.item_question_title);
            tvAuthor = itemView.findViewById(R.id.item_question_author);
            tvTime = itemView.findViewById(R.id.item_question_time);
        }
    }
}
