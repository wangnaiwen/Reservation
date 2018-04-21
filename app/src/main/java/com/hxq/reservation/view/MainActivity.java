package com.hxq.reservation.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import com.hxq.reservation.R;
import com.hxq.reservation.bean.Lecture;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    /**
     * 用户姓名，学号以及头像
     * */
    private TextView ssidTv;
    private TextView nameTv;
    private CircleImageView userIcon;

    private TextView nothingTv;

    RecyclerView mRecyclerView;
    private LectureAdapter lectureAdapter;
    private List<Lecture> lectureList = new ArrayList<>();

    ProgressDialog progressDialog ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    /**
     * 初始化控件
     * */
    private void initView(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("加载中，请稍等...");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mRecyclerView = (RecyclerView)findViewById(R.id.home_rv_timeline);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        lectureAdapter = new LectureAdapter(this, lectureList);
        mRecyclerView.setAdapter(lectureAdapter);

        nothingTv = (TextView)findViewById(R.id.tv_nothing);
        nothingTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //findTodayAttendance();
            }
        });

        ssidTv = (TextView)navigationView.getHeaderView(0).findViewById(R.id.tv_ssid);
        nameTv = (TextView)navigationView.getHeaderView(0).findViewById(R.id.tv_name);

        SharedPreferences sharedPreferences = getSharedPreferences("account",MODE_PRIVATE);
        String sid = sharedPreferences.getString("id", "");
        String name = sharedPreferences.getString("name", "");
        ssidTv.setText(sid);
        nameTv.setText(name);

        userIcon = (CircleImageView)navigationView.getHeaderView(0).findViewById(R.id.icon_user);
        userIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //头像点击
                /*Intent intent = new Intent(MainActivity.this, ImgUploadActivity.class);
                //intent.putExtra("user", user);
                startActivityForResult(intent, 4);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);*/
            }
        });

        //findTodayAttendance();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1001){
            //指纹识别打卡回来
            //findTodayAttendance();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // 菜单选中监听
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, SettingActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
       /* if (id == R.id.nav_setting) {
            Intent intent = new Intent(MainActivity.this, SettingActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }else if (id == R.id.nav_record){
            Intent intent = new Intent(MainActivity.this, RecordActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }else if (id == R.id.nav_attendance){
            Intent intent = new Intent(MainActivity.this, AttendanceActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }*/
        return true;
    }


    //查找今天打卡计划以及打开记录

}