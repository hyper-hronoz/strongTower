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
    public int towardX;
    public int towardY;
    public int enemyWidth = 30;
    private Bitmap arrow;
    private Paint paint;
    private int canvasWidth;

    public Arrow(int towardX, int towardY,int width) {
        this.paint = new Paint();
        this.towardX = towardX;
        this.towardY = towardY;
        this.paint = paint;
        this.canvasWidth = width;
    }

    public boolean isOut(){
        return (arrowXCoordinate > canvasWidth) || (arrowYCoordinate < 0);
    }

    public void changeCoordinate(){
      //Вертикальная и горизонтальная скорость стрелы
        int arrowXSpeed = Math.abs(towardX-arrowXCoordinate)/10;
        int arrowYSpeed = Math.abs(towardY-arrowYCoordinate)/10;

        arrowXCoordinate += arrowXSpeed;
        arrowYCoordinate += arrowYSpeed;

       //Движение стрелы
        if (arrowXCoordinate >= towardX) arrowXCoordinate -=arrowXSpeed;
        if (arrowXCoordinate <= towardX) arrowXCoordinate += arrowXSpeed;
        if (arrowYCoordinate >= towardY) arrowYCoordinate -=arrowYSpeed;
        if (arrowYCoordinate <= towardY) arrowYCoordinate +=arrowYSpeed;

    }

    public void draw(Context context,Canvas canvas) {
        arrow = BitmapFactory.decodeResource(context.getResources(), R.drawable.m_arrow);
        canvas.drawBitmap(arrow, arrowXCoordinate, arrowYCoordinate, paint);
    }

}