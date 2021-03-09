package com.example.strongtower.drawBattlefield;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Timer;
import java.util.TimerTask;

// создаем класс врага
public class Enemy {
    public int enemyXCoordinate = 0;
    public int enemySpeed = 0;
    public String enemyType;
    public int enemyWidth = 30;
    private Canvas canvas;
    private Paint paint;

    public Enemy(int enemySpeed, Canvas canvas) {
        this.paint = new Paint();
        this.enemySpeed = enemySpeed;
        this.canvas = canvas;
        this.paint = paint;
    }

    public void draw() {
        canvas.drawRect(enemyXCoordinate, canvas.getHeight() / 2 + 300, enemyXCoordinate + enemyWidth, canvas.getHeight() / 2 + 200, this.paint);
    }
}
