/*
package com.hxq.reservation.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hxq.reservation.R;
import com.hxq.reservation.bean.Lecture;

import java.util.List;

*/
/**
 * Created by wnw on 2018/4/21.
 *//*


public class LectureAdapter extends RecyclerView.Adapter<LectureAdapter.VHolder>{

    private Context context;
    private List<Lecture> list;

    public LectureAdapter(Context context, List<Lecture> lectures) {
        this.context = context;
        this.list = lectures;
    }

    public void setList(List<Lecture> list) {
        this.list = list;
    }

    @Override
    public LectureAdapter.VHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_lecture, parent, false);
        return new LectureAdapter.VHolder(view);
    }

    @Override
    public void onBindViewHolder(LectureAdapter.VHolder holder, final int position) {
        Lecture lecture = list.get(position);

        */
/*User user = list.get(position);
        holder.sidTv.setText("学号：" + user.getsId());
        holder.nameTv.setText("姓名：" + user.getName());
        holder.passwordTv.setText("密码：" + user.getPassword());
        holder.itemRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity)context).startActivityForResult(new Intent(context, UpdateStudentActivity.class).putExtra(
                        "user", list.get(position)), 1001);
                ((Activity)context).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });*//*

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class VHolder extends RecyclerView.ViewHolder{
        RelativeLayout itemRl;
        TextView sidTv;
        TextView nameTv;
        TextView passwordTv; 

        public VHolder(View itemView) {
            super(itemView);
            sidTv = (TextView)itemView.findViewById(R.id.sid);
            nameTv = (TextView)itemView.findViewById(R.id.name);
            passwordTv = (TextView)itemView.findViewById(R.id.password);
            itemRl = (RelativeLayout)itemView.findViewById(R.id.rl);
        }
    }
}
*/
