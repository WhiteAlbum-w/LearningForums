package com.xiayang.learningforums.frag;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xiayang.learningforums.R;
import com.xiayang.learningforums.bean.RankList;

import java.util.List;

public class ItemRankAdapter extends RecyclerView.Adapter<ItemRankAdapter.RankViewHolder> {

    private Context context;
    private List<RankList> datas;

    public ItemRankAdapter(Context context, List<RankList> datas) {
        this.context = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public RankViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.recycler_item_rank, parent, false);
        RankViewHolder holder = new RankViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RankViewHolder holder, int position) {
        RankList data = datas.get(position);
        //holder.tvId.setText(data.rank);
        //holder.tvIntegral.setText(data.coinCount);
        //holder.tvAuthor.setText(data.username);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    static class RankViewHolder extends RecyclerView.ViewHolder {
        private TextView tvId, tvAuthor, tvIntegral;

        public RankViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.item_rank_id);
            tvAuthor = itemView.findViewById(R.id.item_rank_author);
            tvIntegral = itemView.findViewById(R.id.item_rank_integral);
        }
    }
}
