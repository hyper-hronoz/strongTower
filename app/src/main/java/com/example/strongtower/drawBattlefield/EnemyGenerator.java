package com.example.strongtower.drawBattlefield;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class EnemyGenerator {
    public static ArrayList<Enemy> enemies = new ArrayList<Enemy>();

    public EnemyGenerator(Canvas canvas) {

//        new Timer().scheduleAtFixedRate(new TimerTask(){
//            @Override
//            public void run(){
                Paint paint = new Paint();
                enemies.add(new Enemy(getRandomSpeed(2, 10)));

//                Paint paint2 = new Paint();
//                enemies.add(new Enemy(canvas, paint2));
//
//                for (int i = 0; i < enemies.size(); i++ ) {
//                    System.out.println("Porn" + enemies.get(i));
//                }
//                enemies.add(new Enemy(canvas, paint));
//                enemies.add(enemy);
//            }
//        },0,1000);
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            enemy.enemyXCoordinate += enemy.enemySpeed;

            if (enemy.enemyXCoordinate - 30 > (canvas.getWidth() - 460)) {
                enemy.enemyXCoordinate = -30;
            }

            enemies.set(i, enemy);
            canvas.drawRect(enemies.get(i).enemyXCoordinate, canvas.getHeight() / 2 + 300, enemies.get(i).enemyXCoordinate + 30, canvas.getHeight() / 2 + 200, paint);
        }
    }

    private int getRandomSpeed(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
