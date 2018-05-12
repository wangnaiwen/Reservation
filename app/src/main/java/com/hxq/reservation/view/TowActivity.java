package com.hxq.reservation.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.hxq.reservation.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by wnw on 2018/5/7.
 */

public class TowActivity extends AppCompatActivity {
    private TextView timeTv;
    private String answer[] = new String[]{"10","5","4","6","11","2","1","3", "7","8","9"};

    private static final int GAME_TIMEOUT_TIME = 300;

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


    //答到第一个了
    private int j = 0;

    //计时
    int i = 0;

    private Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tow);
        Log.e("wnw", "第二关");
        initView();
        startTime();
    }

    private void initView(){
        timeTv = (TextView)findViewById(R.id.tv_time);

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

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(1);
                tv1.setBackgroundResource(R.drawable.bg_btn_green);
                tv1.setClickable(false);
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(2);
                tv2.setBackgroundResource(R.drawable.bg_btn_green);
                tv2.setClickable(false);
            }
        });
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(3);
                tv3.setBackgroundResource(R.drawable.bg_btn_green);
                tv3.setClickable(false);
            }
        });
        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(4);
                tv4.setBackgroundResource(R.drawable.bg_btn_green);
                tv4.setClickable(false);
            }
        });
        tv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(5);
                tv5.setBackgroundResource(R.drawable.bg_btn_green);
                tv5.setClickable(false);
            }
        });
        tv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(6);
                tv6.setBackgroundResource(R.drawable.bg_btn_green);
                tv6.setClickable(false);
            }
        });
        tv7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(7);
                tv7.setBackgroundResource(R.drawable.bg_btn_green);
                tv7.setClickable(false);
            }
        });
        tv8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(8);
                tv8.setBackgroundResource(R.drawable.bg_btn_green);
                tv8.setClickable(false);
            }
        });
        tv9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(9);
                tv9.setBackgroundResource(R.drawable.bg_btn_green);
                tv9.setClickable(false);
            }
        });
        tv10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(10);
                tv10.setBackgroundResource(R.drawable.bg_btn_green);
                tv10.setClickable(false);
            }
        });
        tv11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(11);
                tv11.setBackgroundResource(R.drawable.bg_btn_green);
                tv11.setClickable(false);
            }
        });
        createGameOverDialog();
        createDialog();
    }

    //重置所有的视图
    private void resetView(){

        i = 0;
        j = 0;

        timeTv.setText("第一关 ：0S");

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

    }

    //匹配答案
    private boolean checkAnswer(int a){
        if (answer[j].equals(a+"")){
            //答案正确
            if (j == answer.length -1){
                //全部答对，上传记录时间, 跳转到下一关
                uploadGameRecord();
                j ++;
                return true;
            }else{
                //让它变成绿色的并且不可以点击
                j ++;
                return true;
            }
        }else {
            j ++;
            gameOver();
            return false;
        }
    }

    /**
     * 游戏结束
     * */
    private AlertDialog gameDialog;
    private void createGameOverDialog(){
        gameDialog = new AlertDialog.Builder(this).setMessage("抱歉，答案错误，是否再来一局？")
                .setTitle("闯关失败")
                .setPositiveButton("再来一局", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        resetView();
                        restartTime();
                    }
                }).setNegativeButton("不玩了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                }).create();
    }

    private void gameOver(){
        //答案错误,结束游戏
        if (!gameDialog.isShowing()){
            gameDialog.show();
        }
    }

    private AlertDialog dialog;
    private void createDialog(){
        dialog = new AlertDialog.Builder(this).setMessage("抱歉，闯关时间超过" + GAME_TIMEOUT_TIME + "S!!")
                .setTitle("闯关失败")
                .setPositiveButton("再来一局", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        resetView();
                        restartTime();
                    }
                }).setNegativeButton("不玩了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                }).create();
    }
    //游戏超时
    private void gameTimeout(){
        if (!dialog.isShowing() && !gameDialog.isShowing()){
            dialog.show();
        }
    }

    //上传游戏记录
    private void uploadGameRecord(){

    }

    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (i == GAME_TIMEOUT_TIME){
                        gameTimeout();
                    }else {
                        timeTv.setText("第二关 : " + i + "S");
                        i ++;
                    }
                }
            });
        }
    };

    //开始计时
    private void startTime(){
        timer.schedule(timerTask, 1000, 1000);
        i = 0;
        timeTv.setText("第二关 : " + i + "S");
    }

    private void restartTime(){
        i = 0;
        timeTv.setText("第二关 : " + i + "S");
    }
}
