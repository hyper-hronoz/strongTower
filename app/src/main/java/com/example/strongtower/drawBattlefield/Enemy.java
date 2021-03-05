package com.example.strongtower.drawBattlefield;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Timer;
import java.util.TimerTask;

public class Enemy {
    public int enemyXCoordinate = 0;
    public int enemySpeed = 0;

    public Enemy(int enemySpeed) {
        this.enemySpeed = enemySpeed;
    }
}
