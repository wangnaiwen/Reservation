package com.hxq.reservation.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.hxq.reservation.R;
import com.hxq.reservation.bean.Record;
import com.hxq.reservation.bean.Score;
import com.hxq.reservation.bean.User;
import com.hxq.reservation.config.Constans;
import com.hxq.reservation.util.ScoreUtil;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by wnw on 2018/5/7.
 */

public class TowActivity extends AppCompatActivity {
    private TextView timeTv;
    private String answer[] = new String[]{"10","5","4","6","11","2","1","3", "7","8","9"};

    private static final int GAME_TIMEOUT_TIME = Constans.GAME_TIME_OUT;

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
    private boolean isSuccessed = false;
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
                isSuccessed = true;
                createGameSuccessDialog(i);
                uploadGameRecord(i);
                updateGameId();
                updateScore(i);
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
        if (!isSuccessed){
            if (!dialog.isShowing() && !gameDialog.isShowing()){
                dialog.show();
            }
        }
    }

    //上传游戏记录
    private void uploadGameRecord(int time){
        SharedPreferences sharedPreferences = getSharedPreferences("account", MODE_PRIVATE);

        Record record = new Record();
        record.setGameId(sharedPreferences.getInt("gameId", 0) + 1);
        record.setNickName(sharedPreferences.getString("nickname", ""));
        record.setUserId(sharedPreferences.getString("id", ""));
        record.setScore(ScoreUtil.getScore(time));
        record.setTime(time);
        record.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e != null){
                    e.printStackTrace();
                }
            }
        });
    }

    private AlertDialog gameSuccessDialog;
    private void createGameSuccessDialog(int time){
        gameSuccessDialog = new AlertDialog.Builder(this)
                .setMessage("恭喜您，闯关成功，" + "获得" + ScoreUtil.getScore(time) + "分")
                .setTitle("闯关成功")
                .setPositiveButton("下一关", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(TowActivity.this, ThreeActivity.class));
                        finish();
                    }
                })
                .setNegativeButton("休息一下", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                }).create();
        gameSuccessDialog.show();
    }

    //更新用户到达哪一关
    private void updateGameId(){
        SharedPreferences sharedPreferences = getSharedPreferences("account", MODE_PRIVATE);
        User user = new User();
        user.setObjectId(sharedPreferences.getString("id", ""));
        user.setPhone(sharedPreferences.getString("phone", ""));
        user.setNickName(sharedPreferences.getString("nickname", ""));
        user.setPassword(sharedPreferences.getString("password", ""));
        user.setGameId(sharedPreferences.getInt("gameId", 0) + 1);
        BmobFile bmobFile = new BmobFile("user.png","","http://bmob-cdn-11142.b0.upaiyun.com/2017/05/04/fad104a0401e41ae80fa90ad05c62240.png");
        user.setImage(bmobFile);
        user.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e != null){
                    e.printStackTrace();
                }
            }
        });
        SharedPreferences.Editor editor = getSharedPreferences("account",
                MODE_PRIVATE).edit();
        editor.putInt("gameId", user.getGameId());
        editor.apply();
    }

    private Score score;
    private void updateScore(final int time){
        SharedPreferences sharedPreferences = getSharedPreferences("account", MODE_PRIVATE);
        String userId = sharedPreferences.getString("id", "");
        BmobQuery<Score> query = new BmobQuery<>();
        query.addWhereEqualTo("userId", userId);
        query.findObjects(new FindListener<Score>() {
            @Override
            public void done(List<Score> list, BmobException e) {
                if (list != null && e == null && list.size() != 0){
                    score = list.get(0);
                    updateGameScore(time);
                }
            }
        });
    }

    //上传到总分榜
    private void updateGameScore(int time){
        score.setScore(ScoreUtil.getScore(time) + score.getScore());
        score.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e != null){
                    e.printStackTrace();
                }
            }
        });
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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        timerTask.cancel();
        timer.cancel();
    }

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
