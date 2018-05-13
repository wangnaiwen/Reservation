package com.hxq.reservation.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.hxq.reservation.R;

/**
 * Created by wnw on 2018/5/13.
 */

public class GameRuleActivity extends AppCompatActivity{
    private TextView text;

    private String rule = "烽火戏诸侯游戏规则：\n\r1. 玩游戏时的场景，从中心块周围开始，即周围的四片，按照面积从小到大点击，在规定时间内点击所有方块，顺序正确则闯关成功\n\r2.闯关时间为20S，超过20S被认定为闯关失败\n\r3.得分规则如下：\n\r (1) 闯关时间大于15S少于20S,得10分 \r\n  (2) 闯关时间大于12S少于15S,得12分 \n" +
            "  (3) 闯关时间大于10S少于12S,得15分 \n\r (4) 闯关时间大于8S少于10S,得18分 \n\r (5) 闯关时间少于8S, 得20分" ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_rule);
        text = (TextView)findViewById(R.id.text);
        text.setText(rule);
    }
}
