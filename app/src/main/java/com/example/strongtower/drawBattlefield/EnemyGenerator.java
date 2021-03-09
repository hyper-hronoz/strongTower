package com.example.strongtower.drawBattlefield;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

// генерирование врагов
public class EnemyGenerator {

    public EnemyGenerator(Canvas canvas) {

        // случайная скорость
        if (getRandomNumber(0, 301) % 300 == 0) {
            GameCore.enemies.add(new Enemy(getRandomNumber(2, 10), canvas));
        }
    }

    // получение рандомного числа
    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
