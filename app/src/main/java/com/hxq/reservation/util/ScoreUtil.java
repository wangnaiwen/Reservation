package com.hxq.reservation.util;

/**
 * Created by wnw on 2018/5/13.
 */

public class ScoreUtil {

    public static int getScore(int time){
         if (time > 15){
             return 10;
         }else if (time > 12){
             return 12;
         }else if (time > 10){
             return 15;
         }else if(time > 8){
             return 18;
         }else{
             return 18;
         }
    }
}
