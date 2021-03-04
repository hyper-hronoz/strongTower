package com.example.strongtower.drawBattlefield;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.SurfaceHolder;

import com.example.strongtower.MainActivity;

public class DrawBattlefield extends Thread {

    private SurfaceHolder surfaceHolder;

    private volatile boolean running = true; //флаг для остановки потока

    private int towardPointX;
    private int towardPointY;

    public void setTowardPoint(int x, int y) {
        towardPointX = x;
        towardPointY = y;
    }

    public DrawBattlefield(Context context, SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
    }

    public void requestStop() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            Canvas canvas = surfaceHolder.lockCanvas();
            if (canvas != null) {
                try {
                    Paint paint = new Paint();

                    // ХОЛСТ
                    paint.setAntiAlias(true); // сглаживание
                    paint.setStyle(Paint.Style.FILL);
                    paint.setColor(Color.rgb(128, 166, 255));
                    canvas.drawPaint(paint);

                    // Солнце
                    new Sun(canvas, paint);

                    // Земля
                    new Ground(canvas, paint);

                    // Замок
                    new Castle(canvas, paint);

                    // Враг
                    new Enemy(canvas, paint);

                } finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}
