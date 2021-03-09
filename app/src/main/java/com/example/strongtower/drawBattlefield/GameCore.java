package com.example.strongtower.drawBattlefield;

import android.graphics.Canvas;

import java.util.ArrayList;

public class GameCore {
    public static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private Canvas canvas;

    public GameCore(Canvas canvas) {
        this.canvas = canvas;

        // ставим ограничение на создание новых врагов
        if (enemies.size() < 4) {
            // Генератор врагов
            new EnemyGenerator(canvas);
        }


        // Крепость
        new Castle(this.canvas);

        // отрисовывыем врагов
        drawEnemies();
    }

    // рисовка всех врагов
    public void drawEnemies() {
        for (Enemy enemy : enemies) {
            enemy.enemyXCoordinate += enemy.enemySpeed;

            if (enemy.enemyXCoordinate - 30 > (canvas.getWidth() - 460)) {
                enemy.enemyXCoordinate = -1000;
            }

            enemy.draw();
        }
    }
}
