package com.example.strongtower.drawBattlefield;

import android.graphics.Canvas;

import java.util.ArrayList;

public class GameCore {
    public static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    // массив со стрелами
    public static ArrayList<Arrow> arrows = new ArrayList<Arrow>();
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
        drawArrows();
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
    public void drawArrows() {
        for (Arrow arrow : arrows) {
            arrow.arrowXCoordinate += arrow.arrowXSpeed;
            arrow.arrowYCoordinate += arrow.arrowYSpeed;
            // Движение стрелы
            if (arrow.arrowXCoordinate >= DrawBattlefield.towardPointX) arrow.arrowXCoordinate -=arrow.arrowXSpeed;
            if (arrow.arrowXCoordinate <= DrawBattlefield.towardPointX) arrow.arrowXCoordinate += arrow.arrowXSpeed;
            if (arrow.arrowYCoordinate >= DrawBattlefield.towardPointY) arrow.arrowYCoordinate -=arrow.arrowYSpeed;
            if (arrow.arrowYCoordinate <= DrawBattlefield.towardPointY) arrow.arrowYCoordinate +=arrow.arrowYSpeed;

            arrow.draw(context);
        }
    }
}
