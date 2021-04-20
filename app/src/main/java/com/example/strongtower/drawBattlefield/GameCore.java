package com.example.strongtower.drawBattlefield;

import android.content.Context;
import android.graphics.Canvas;

import java.util.ArrayList;

public class GameCore {
    public static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    // массив со стрелами
    public static ArrayList<Arrow> arrows = new ArrayList<Arrow>();
    private Canvas canvas;
    private Context context;




    public GameCore(Canvas canvas,Context context) {
        this.canvas = canvas;
        this.context = context;

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

    public void CreateArrow(int x, int y){
        arrows.add(new Arrow(x,y,canvas.getWidth()));
    }

    public void coordinate_delete_arrow() {
        for (int i = 0; i < arrows.size(); i ++){
            if (arrows.get(i).isOut()){
                arrows.remove(i);
            }
            arrows.get(i).changeCoordinate();
        }
    }
}
