package com.hxq.reservation.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hxq.reservation.R;
import com.hxq.reservation.bean.Score;

import java.util.List;

/**
 * Created by wnw on 2018/5/13.
 */

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.MyViewHolder>{

    private Context context;
    private List<Score> scoreList;

    public ScoreAdapter(Context context, List<Score> scores) {
        this.context = context;
        this.scoreList = scores;
    }

    public void setScoreList(List<Score> scoreList){
        this.scoreList = scoreList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_score, parent, false);
        return new ScoreAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Score score = scoreList.get(position);
        if (position == 0){
            holder.numTv.setBackgroundResource(R.drawable.bg_clc_yellow);
        }else if (position == 1){
            holder.numTv.setBackgroundResource(R.drawable.bg_clc_grap);
        }else if (position == 2){
            holder.numTv.setBackgroundResource(R.drawable.bg_clc_copper);
        }else {
            holder.numTv.setBackgroundResource(R.drawable.bg_clc_bule);
        }
        holder.numTv.setText((position + 1) + "");
        holder.nickNameTv.setText(score.getNickname());
        holder.scoreTv.setText(score.getScore() + " 分" );
    }

    @Override
    public int getItemCount() {
        return scoreList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView numTv;
        TextView scoreTv;
        TextView nickNameTv;

        public MyViewHolder(View itemView) {
            super(itemView);
            numTv = (TextView)itemView.findViewById(R.id.num);
            scoreTv = (TextView)itemView.findViewById(R.id.score);
            nickNameTv = (TextView)itemView.findViewById(R.id.nickname);
        }
    }
}
