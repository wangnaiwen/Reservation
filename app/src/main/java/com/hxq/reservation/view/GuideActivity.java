package com.hxq.reservation.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.hxq.reservation.R;
import com.hxq.reservation.config.Constans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;

/**
 * Created by wnw on 2018/5/27.
 */

public class GuideActivity extends AppCompatActivity{

    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;
    private TextView tv6;
    private TextView tv7;
    private TextView tv8;
    private TextView tv9;
    private TextView tv10;
    private TextView tv11;
    private TextView tv12;
    private TextView tv13;
    private TextView tv14;
    private TextView tv15;
    private TextView tv16;
    private TextView tv17;
    private TextView tv18;
    private TextView tv19;
    private TextView tv20;
    private TextView tv21;
    private TextView tv22;
    private TextView tv23;

    //答案
    private String[] l1 = new String[]{"4", "11", "14", "15"};
    private String[] l2 = new String[]{"3", "7", "9", "17"};
    private String[] l3 = new String[]{"16", "18", "19"};
    private String[] l4 = new String[]{"10", "13"};
    private String[] l5 = new String[]{"20", "21", "22", "23"};
    private String[] l6 = new String[]{"2","1","5"};
    private String[] l7 = new String[]{"6", "8"};
    private String[] l8 = new String[]{"12"};


    private Map<Integer, String[]> map = new HashMap<>();
    private Map<String, TextView> map1 = new HashMap<>();

    private boolean isCancel = false;


    //第几个
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        SharedPreferences.Editor editor = getSharedPreferences("account",MODE_PRIVATE).edit();
        editor.putBoolean("fistTime", false);
        editor.apply();
        Log.e("wnw", "新手引导");
        initView();
        initData();
        delay();
    }

    private void initData(){
        map.put(1, l1);
        map.put(2, l2);
        map.put(3, l3);
        map.put(4, l4);
        map.put(5, l5);
        map.put(6, l6);
        map.put(7, l7);
        map.put(8, l8);

        map1.put("1", tv1);
        map1.put("2", tv2);
        map1.put("3", tv3);
        map1.put("4", tv4);
        map1.put("5", tv5);
        map1.put("6", tv6);
        map1.put("7", tv7);
        map1.put("8", tv8);
        map1.put("9", tv9);
        map1.put("10", tv10);
        map1.put("11", tv11);
        map1.put("12", tv12);
        map1.put("13", tv13);
        map1.put("14", tv14);
        map1.put("15", tv15);
        map1.put("16", tv16);
        map1.put("17", tv17);
        map1.put("18", tv18);
        map1.put("19", tv19);
        map1.put("20", tv20);
        map1.put("21", tv21);
        map1.put("22", tv22);
        map1.put("23", tv23);
    }

    private void initView(){
        tv1 = (TextView)findViewById(R.id.tv1);
        tv2 = (TextView)findViewById(R.id.tv2);
        tv3 = (TextView)findViewById(R.id.tv3);
        tv4 = (TextView)findViewById(R.id.tv4);
        tv5 = (TextView)findViewById(R.id.tv5);
        tv6 = (TextView)findViewById(R.id.tv6);
        tv7 = (TextView)findViewById(R.id.tv7);
        tv8 = (TextView)findViewById(R.id.tv8);
        tv9 = (TextView)findViewById(R.id.tv9);
        tv10 = (TextView)findViewById(R.id.tv10);
        tv11 = (TextView)findViewById(R.id.tv11);
        tv12 = (TextView)findViewById(R.id.tv12);
        tv13 = (TextView)findViewById(R.id.tv13);
        tv14 = (TextView)findViewById(R.id.tv14);
        tv15 = (TextView)findViewById(R.id.tv15);
        tv16 = (TextView)findViewById(R.id.tv16);
        tv17 = (TextView)findViewById(R.id.tv17);
        tv18 = (TextView)findViewById(R.id.tv18);
        tv19 = (TextView)findViewById(R.id.tv19);
        tv20 = (TextView)findViewById(R.id.tv20);
        tv21 = (TextView)findViewById(R.id.tv21);
        tv22 = (TextView)findViewById(R.id.tv22);
        tv23 = (TextView)findViewById(R.id.tv23);
    }

    /**
     * 开始游戏,递归遍历所有的答案
     * */
    private void startGame(String[] s){
        int length = s.length;
        Log.e("wnw", "startGame" + length + " " + i);
        i ++;
        playSound();
        for (int j = 0; j < length; j ++){
            //点亮其周围的点
            TextView tv = map1.get(s[j]);
            tv.setClickable(false);
            tv.setBackgroundResource(R.drawable.bg_btn_green);
        }
        delay();
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (i == 8){
                //说明已经结束
                gameOver();
                return;
            }else {
                String[] next = map.get(i+1);
                startGame(next);
            }
        }
    };

    private void delay(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    if (!isCancel){
                        handler.sendMessage(new Message());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //播放声音
    MediaPlayer music;
    private void playSound(){
        if (music == null){
            music = MediaPlayer.create(this, R.raw.qipao);
        }
        music.start();
    }

    private AlertDialog gameSuccessDialog;
    private void gameOver(){
        gameSuccessDialog = new AlertDialog.Builder(this)
                .setMessage("新手引导结束，是否再来一遍？")
                .setTitle("新手引导结束")
                .setPositiveButton("再来一遍", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        resetView();
                        startGame(l1);
                    }
                })
                .setNegativeButton("进入游戏", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                }).create();
        gameSuccessDialog.show();
    }

    //重置所有的视图
    private void resetView(){

        i = 0;

        tv1.setClickable(true);
        tv1.setBackgroundResource(R.drawable.bg_btn);

        tv2.setClickable(true);
        tv2.setBackgroundResource(R.drawable.bg_btn);


        tv3.setClickable(true);
        tv3.setBackgroundResource(R.drawable.bg_btn);

        tv4.setClickable(true);
        tv4.setBackgroundResource(R.drawable.bg_btn);

        tv5.setClickable(true);
        tv5.setBackgroundResource(R.drawable.bg_btn);

        tv6.setClickable(true);
        tv6.setBackgroundResource(R.drawable.bg_btn);

        tv7.setClickable(true);
        tv7.setBackgroundResource(R.drawable.bg_btn);

        tv8.setClickable(true);
        tv8.setBackgroundResource(R.drawable.bg_btn);

        tv9.setClickable(true);
        tv9.setBackgroundResource(R.drawable.bg_btn);

        tv10.setClickable(true);
        tv10.setBackgroundResource(R.drawable.bg_btn);

        tv11.setClickable(true);
        tv11.setBackgroundResource(R.drawable.bg_btn);

        tv12.setClickable(true);
        tv12.setBackgroundResource(R.drawable.bg_btn);


        tv13.setClickable(true);
        tv13.setBackgroundResource(R.drawable.bg_btn);

        tv14.setClickable(true);
        tv14.setBackgroundResource(R.drawable.bg_btn);

        tv15.setClickable(true);
        tv15.setBackgroundResource(R.drawable.bg_btn);

        tv16.setClickable(true);
        tv16.setBackgroundResource(R.drawable.bg_btn);

        tv17.setClickable(true);
        tv17.setBackgroundResource(R.drawable.bg_btn);

        tv18.setClickable(true);
        tv18.setBackgroundResource(R.drawable.bg_btn);

        tv19.setClickable(true);
        tv19.setBackgroundResource(R.drawable.bg_btn);

        tv20.setClickable(true);
        tv20.setBackgroundResource(R.drawable.bg_btn);

        tv21.setClickable(true);
        tv21.setBackgroundResource(R.drawable.bg_btn);

        tv22.setClickable(true);
        tv22.setBackgroundResource(R.drawable.bg_btn);

        tv23.setClickable(true);
        tv23.setBackgroundResource(R.drawable.bg_btn);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isCancel = true;
    }
}
