package com.hxq.reservation.bean;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * Created by wnw on 2018/5/12.
 */

public class Score extends BmobObject implements Serializable{
    private String userId;
    private String nickname;
    private int score;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
