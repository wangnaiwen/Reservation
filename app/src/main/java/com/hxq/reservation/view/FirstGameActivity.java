package com.hxq.reservation.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.hxq.reservation.R;


/**
 * Created by wnw on 2018/5/1.
 */

public class FirstGameActivity extends Activity{

    private TextView timeTv;
    private String answer[] = new String[]{"6","5","8","9","4","3","1","11","7","10"};

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        initView();
    }

    private void initView(){
        timeTv = (TextView)findViewById(R.id.tv_time);
        startTime();

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

    }

    //重置所有的视图
    private void resetView(){

        i = 0;
        j = 0;

        timeTv.setText("第一关：0S");

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
            //答案错误,结束游戏
            new AlertDialog.Builder(this).setMessage("抱歉，答案错误，是否再来一局？")
                    .setTitle("闯关失败")
                    .setPositiveButton("再来一局", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            resetView();
                            startTime();
                        }
                    }).setNegativeButton("不玩了", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
            }).show();
            return false;
        }
    }

    //上传游戏记录
    private void uploadGameRecord(){

    }

    //计时
    private void startTime(){
        i = 0;
        while (i != 300)
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    i ++;
                    handler.sendMessage(new Message());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            timeTv.setText("第一关" + i + "S");
            return false;
        }
    });
}
