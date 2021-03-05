package com.example.strongtower.drawBattlefield;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

// генерирование врагов
public class EnemyGenerator {
    public static ArrayList<Enemy> enemies = new ArrayList<Enemy>();

    public EnemyGenerator(Canvas canvas) {
        Paint paint = new Paint();

        // случайная скорость
        if (getRandomNumber(0, 301) % 300 == 0) {
            enemies.add(new Enemy(getRandomNumber(2, 10)));
        }

        // рисовка всех врагов
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            enemy.enemyXCoordinate += enemy.enemySpeed;

            if (enemy.enemyXCoordinate - 30 > (canvas.getWidth() - 460)) {
                enemy.enemyXCoordinate = -1000;
            }

            enemies.set(i, enemy);
            canvas.drawRect(enemies.get(i).enemyXCoordinate, canvas.getHeight() / 2 + 300, enemies.get(i).enemyXCoordinate + 30, canvas.getHeight() / 2 + 200, paint);
        }
    }

    // получение рандомного числа
    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
