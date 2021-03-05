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

    public Enemy(int enemySpeed) {
        this.enemySpeed = enemySpeed;
    }
}
