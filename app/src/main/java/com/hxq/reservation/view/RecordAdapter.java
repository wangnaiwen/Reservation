package com.hxq.reservation.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.hxq.reservation.R;
import com.hxq.reservation.bean.Record;

import java.util.List;

/**
 * Created by wnw on 2018/4/1.
 */

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.RecordHolder>{
    private Context context;
    private List<Record> recordList;

    public RecordAdapter(Context context, List<Record> records) {
        this.context = context;
        this.recordList = records;
    }

    public void setRecordList(List<Record> records) {
        this.recordList = records;
    }

    @Override
    public RecordAdapter.RecordHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_record, parent, false);
        return new RecordAdapter.RecordHolder(view);
    }

    @Override
    public void onBindViewHolder(RecordAdapter.RecordHolder holder, int position) {
        Record record = recordList.get(position);
        holder.timeTv.setText("闯关用时：" + record.getTime() + " S");
        holder.gameIdTv.setText("闯关关卡：" + "第" + record.getGameId()  + "关");
        holder.scoreTv.setText("闯关得分：" + record.getScore());
    }

    @Override
    public int getItemCount() {
        return recordList.size();
    }

    class RecordHolder extends RecyclerView.ViewHolder{
        TextView timeTv;
        TextView scoreTv;
        TextView gameIdTv;

        public RecordHolder(View itemView) {
            super(itemView);
            timeTv = (TextView)itemView.findViewById(R.id.time);
            scoreTv = (TextView)itemView.findViewById(R.id.score);
            gameIdTv = (TextView)itemView.findViewById(R.id.gameid);
        }
    }
}
