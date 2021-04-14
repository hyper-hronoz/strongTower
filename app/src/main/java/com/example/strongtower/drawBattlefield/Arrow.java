package com.example.strongtower.drawBattlefield;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.strongtower.R;


public class Arrow {
    public int arrowXCoordinate = 1800;
    public int arrowYCoordinate = 540;
    public int arrowXSpeed = 0;
    public int arrowYSpeed = 0;
    public int enemyWidth = 30;
    private Bitmap arrow;
    private Canvas canvas;
    private Paint paint;


    public Arrow(int arrowXSpeed, int arrowYSpeed,  Canvas canvas) {
        this.paint = new Paint();
        this.arrowYSpeed = arrowYSpeed;
        this.arrowXSpeed = arrowXSpeed;
        this.canvas = canvas;
        this.paint = paint;
    }

    public void draw(Context context) {
        arrow = BitmapFactory.decodeResource(context.getResources(), R.drawable.m_arrow);
        canvas.drawBitmap(arrow, arrowXCoordinate, arrowYCoordinate, paint);
    }
}