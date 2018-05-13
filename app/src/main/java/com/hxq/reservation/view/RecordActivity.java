package com.hxq.reservation.view;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import com.hxq.reservation.R;
import com.hxq.reservation.bean.Record;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.qqtheme.framework.picker.DatePicker;

/**
 * Created by wnw on 2018/3/23.
 */

public class RecordActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecordAdapter recordAdapter;

    private List<Record> recordList = new ArrayList<>();

    private TextView nothingTv;

    ProgressDialog progressDialog ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        initView();
    }

    /**
     * 初始化View
     * */
    private void initView(){
        recyclerView = (RecyclerView)findViewById(R.id.recycler);
        nothingTv = (TextView)findViewById(R.id.tv_nothing);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("加载中，请稍等...");

        findRecord();

        nothingTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findRecord();
            }
        });

        recordAdapter = new RecordAdapter(RecordActivity.this, recordList);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recordAdapter);
    }

    private void findRecord(){
        progressDialog.show();
        BmobQuery<Record> query = new BmobQuery<Record>();
        SharedPreferences sharedPreferences = getSharedPreferences("account", MODE_PRIVATE);
        query.addWhereEqualTo("userId", sharedPreferences.getString("id", ""));

        query.findObjects(new FindListener<Record>() {
            @Override
            public void done(List<Record> list, BmobException e) {
                if (e == null && list.size() > 0){
                    //刷新视图
                    progressDialog.dismiss();
                    Log.e("AttendanceTAG", " " + list.size());
                    recordList = list;
                    recyclerView.setVisibility(View.VISIBLE);
                    nothingTv.setVisibility(View.GONE);
                    recordAdapter.setRecordList(recordList);
                    recordAdapter.notifyDataSetChanged();
                }else{
                    progressDialog.dismiss();
                    e.printStackTrace();
                    recyclerView.setVisibility(View.GONE);
                    nothingTv.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
