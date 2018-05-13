package com.hxq.reservation.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by wnw on 2018/3/13.
 */

public class Record extends BmobObject {
    private String userId;
    private String nickName;
    private int time;
    private int score;
    private int gameId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
