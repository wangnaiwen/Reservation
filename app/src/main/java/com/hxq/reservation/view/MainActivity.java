package com.hxq.reservation.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.hxq.reservation.R;
import com.hxq.reservation.bean.Score;
import com.hxq.reservation.service.MusicService;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
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
    private TextView startGameTv;
    private RecyclerView mRecyclerView;
    private ScoreAdapter scoreAdapter;
    private List<Score> scoreList = new ArrayList<>();

    ProgressDialog progressDialog ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("rule", MODE_PRIVATE);
        if (sharedPreferences.getBoolean("firstTime",true)){
            startActivity(new Intent(this, GameRuleActivity.class));
            SharedPreferences.Editor editor = getSharedPreferences("rule",
                    MODE_PRIVATE).edit();
            editor.putBoolean("firstTime", false);
            editor.apply();
        }
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

        mRecyclerView = (RecyclerView)findViewById(R.id.recycler);
        scoreAdapter = new ScoreAdapter(this, scoreList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(scoreAdapter);

        startGameTv = (TextView)findViewById(R.id.start_game);
        nothingTv = (TextView)findViewById(R.id.tv_nothing);
        nothingTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findScore();
            }
        });

        startGameTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Game", "用户点击开始游戏");
                startGame();
            }
        });

        ssidTv = (TextView)navigationView.getHeaderView(0).findViewById(R.id.tv_ssid);
        nameTv = (TextView)navigationView.getHeaderView(0).findViewById(R.id.tv_name);

        SharedPreferences sharedPreferences = getSharedPreferences("account",MODE_PRIVATE);
        String sid = sharedPreferences.getString("phone", "");
        String name = sharedPreferences.getString("nickname", "");
        ssidTv.setText(sid);
        nameTv.setText(name);

        userIcon = (CircleImageView)navigationView.getHeaderView(0).findViewById(R.id.icon_user);
        userIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    //开始游戏
    private void startGame(){
        SharedPreferences sharedPreferences = getSharedPreferences("account", MODE_PRIVATE);
        int gameId = sharedPreferences.getInt("gameId", 0);
        Log.e("Game", "开始游戏"+ gameId );
        if (gameId == 0){
            startActivity(new Intent(this, FirstGameActivity.class));
        }else if (gameId == 1){
            startActivity(new Intent(this, TowActivity.class));
        }else if (gameId == 2){
            startActivity(new Intent(this, ThreeActivity.class));
        }else if (gameId == 3){
            startActivity(new Intent(this, FourActivity.class));
        }else if (gameId == 4){
            startActivity(new Intent(this, FiveActivity.class));
        }else {
            Toast.makeText(MainActivity.this, "您已通关，更多游戏，敬请期待！ ", Toast.LENGTH_SHORT).show();
        }
    }


    //查看排行榜:降序
    private void findScore(){
        progressDialog.show();
        BmobQuery<Score> query = new BmobQuery<>();
        query.order("-score");
        query.findObjects(new FindListener<Score>() {
            @Override
            public void done(List<Score> list, BmobException e) {
                progressDialog.dismiss();
                if (list == null && e != null && list.size() == 0){
                    Log.e("Score", "list is null");
                    nothingTv.setVisibility(View.VISIBLE);
                    mRecyclerView.setVisibility(View.GONE);
                }else {
                    Log.e("Score", "list size is" + scoreList.size());
                    mRecyclerView.setVisibility(View.VISIBLE);
                    nothingTv.setVisibility(View.GONE);
                    scoreList = list;
                    scoreAdapter.setScoreList(scoreList);
                    scoreAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    /**
     * 背景音乐
     * */
    private void playMusic(){
        SharedPreferences sharedPreferences = getSharedPreferences("music", MODE_PRIVATE);
        if (sharedPreferences.getBoolean("music", false)){
            Intent intent = new Intent(this, MusicService.class);
            startService(intent);
        }else {
            stopMusic();
        }
    }

    /**
     * 停止背景音乐
     * */
    private void stopMusic(){
        Intent intent = new Intent(this, MusicService.class);
        stopService(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this, MusicService.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        playMusic();
        findScore();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1001){

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
        }else if (id == R.id.action_game_rule){
            Intent intent = new Intent(MainActivity.this, GameRuleActivity.class);
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
       if (id == R.id.nav_setting) {
            Intent intent = new Intent(MainActivity.this, SettingActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }else if (id == R.id.nav_record){
            Intent intent = new Intent(MainActivity.this, RecordActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
        return true;
    }
}
