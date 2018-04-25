package com.hxq.reservation.view.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wnw on 2018/4/23.
 */

public class First extends View{

    public First(Context context) {
        super(context);
    }

    public First(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Paint p = new Paint();
        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.BLACK);

        Path path = new Path();
        path.moveTo(80, 120);
        path.moveTo(80, 250);
        path.moveTo(120, 250);
        path.close();
        canvas.drawPath(path, p);
    }
}
