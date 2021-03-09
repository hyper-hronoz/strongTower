package com.example.strongtower.drawBattlefield;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.SurfaceHolder;

import com.example.strongtower.MainActivity;
import com.example.strongtower.R;

public class DrawBattlefield extends Thread {
    // здесь хранятся все о статической отрисовке(все то что не меняется)
    public int groundHeight = 0;
    public int towerHeight = 0;

    private SurfaceHolder surfaceHolder;

    private volatile boolean running = true; //флаг для остановки потока
    private Bitmap bow;
    private int towardPointX;
    private int towardPointY;

    public void setTowardPoint(int x, int y) {
        towardPointX = x;
        towardPointY = y;
    }

    // Функция для подсчета угла между точками
    public static float calculateAngle(float x1, float y1, float x2, float y2) {
        float angle = (float) Math.toDegrees(Math.atan2(x2 - x1, y2 - y1));
        angle = (float) (angle + Math.ceil(- angle / 360) *360);
        return angle;
    }

    public DrawBattlefield(Context context, SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
        /* Добавляем картинку лука */
        bow = BitmapFactory.decodeResource(context.getResources(), R.drawable.bow);
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



                    // Логига игры
                    new GameCore(canvas);

                    //***********************************************************************************************//

                    /* Рисуем лук */
                    int bow_x = canvas.getWidth()/2 + 675 + 15;
                    int bow_y = canvas.getHeight()/2 - 275 + 75;

                    float rotate_bow_x_center = bow_x ; //центр поворота по оси X + (bow.getWidth()/2) т.к LANDSCAPE -- это Y!!! -- ТУТ ВСЁ ЗАЕБИСЬ, РАБОТАЕМ С X
                    float rotate_bow_y_center = bow_y + 200; // центр поворота по оси Y + (bow.getHeight()/2) -- ТУТ ВСЁ ЗАЕБИСЬ, РАБОТАЕМ С X

                    //Находим угол по двум точкам
                    float rotate_bow_angle = calculateAngle(towardPointX, towardPointY, rotate_bow_x_center, rotate_bow_y_center);

                    //Поворачиваем холст
                    canvas.rotate(-(rotate_bow_angle-90), rotate_bow_x_center, rotate_bow_y_center);
                    //Рисуем лук
                    canvas.drawBitmap(bow, bow_x, bow_y, paint);
                    //Переворачиваем холст обратно, на прежний угол
                    canvas.rotate(rotate_bow_angle-90, rotate_bow_x_center, rotate_bow_y_center);

                    //***********************************************************************************************//



                } finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}
