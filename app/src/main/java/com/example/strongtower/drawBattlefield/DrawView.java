package com.example.strongtower.drawBattlefield;

import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class DrawView extends SurfaceView implements SurfaceHolder.Callback {

    private DrawBattlefield drawBattlefield;
    private GameCore gameCore;

    public DrawView(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        drawBattlefield = new DrawBattlefield(getContext(), getHolder());
        drawBattlefield.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        drawBattlefield.requestStop();
        boolean retry = true;
        while (retry) {
            try {
                drawBattlefield.join();
                retry = false;
            } catch (InterruptedException e) {
                //
            }
        }
    }

    // здесь у нас отслеживаются нажатия пользователя на экран
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        drawBattlefield.setTowardPoint((int) event.getX(), (int) event.getY());

//        try {
//            for (Enemy enemy : GameCore.enemies) {
//                if (enemy.enemyXCoordinate <= (int) drawBattlefield.arrow_x && enemy.enemyXCoordinate + enemy.enemyWidth >= (int) drawBattlefield.arrow_x) {
//                    System.out.println("Есть пробитие");
//                    GameCore.enemies.remove(enemy);
//                }
//            }
//        } catch (Exception e) {
//
//        }
        return false;
    }
}
