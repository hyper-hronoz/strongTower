package com.example.strongtower;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.SurfaceHolder;

public class DrawThread extends Thread {

    private SurfaceHolder surfaceHolder;

    private volatile boolean running = true;//флаг для остановки потока
    private Paint backgroundPaint = new Paint();


    private Bitmap bitmap;
    private int towardPointX;
    private int towardPointY;

    {
//        backgroundPaint.setColor(Color.BLUE);
//        backgroundPaint.setStyle(Paint.Style.FILL);
    }

    public DrawThread(Context context, SurfaceHolder surfaceHolder) {
        //bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.smile);
        this.surfaceHolder = surfaceHolder;

    }

    public void requestStop() {
        running = false;
    }

    public void setTowardPoint(int x, int y) {
        towardPointX = x;
        towardPointY = y;
    }

    @Override
    public void run() {
        boolean is_moon = false;
        int smileX = 0;
        int smileY = 400;

        //движение человечков
        int manX = 0;

        while (running) {
            Canvas canvas = surfaceHolder.lockCanvas();
            if (canvas != null) {
                try {
                    Paint paint = new Paint();
                    Paint colorHeaven = new Paint();
                    Path path = new Path();
                    //Антиальясинг (сглаживание)
                    paint.setAntiAlias(true);

                    //ХОЛСТ
                    // Выбираем кисть
                    paint.setStyle(Paint.Style.FILL);
                    // Белый цвет кисти
                    paint.setColor(Color.rgb(128, 166, 255));
                    // Закрашиваем холст
                    canvas.drawPaint(paint);


                    // День или ночь
                    if (!is_moon) {
                        paint.setColor(Color.rgb(255, 249, 23));
                        canvas.drawCircle(smileX, smileY, 175, paint);
                    } else {
                        paint.setColor(Color.rgb(58, 61, 119));
                        canvas.drawPaint(paint);

                        paint.setColor(Color.rgb(216, 235, 255));
                        canvas.drawCircle(smileX, smileY, 175, paint);
                    }

                    //smileY = -(smileX*smileX);

                    //Движение по оси Y
                    if (smileX <= canvas.getWidth()/2)  smileY -= 1;
                    if (smileX > canvas.getWidth()/2) smileY += 1;

                    // Движение по оси X
                    if (smileX - 200 <= canvas.getWidth()) smileX += 1;
                    if (smileX - 200 > canvas.getWidth()) {
                        smileX = - 200;
                        smileX += 1;
                        is_moon = !(is_moon);
                    }




                    // Земля
                    //paint.setColor(Color.rgb(63, 155, 11));
                    paint.setColor(Color.rgb(33, 135, 11));
                    canvas.drawRect(0, canvas.getHeight() / 2 + 300, canvas.getWidth(), canvas.getHeight(), paint);

                    // Бункер
                    paint.setColor(Color.rgb(91, 57, 23));
                    canvas.drawRect(canvas.getWidth() - 400, canvas.getHeight() / 2 + 300, canvas.getWidth(), canvas.getHeight() / 2, paint);

                    // человечки
                    paint.setColor(Color.rgb(41, 54, 43));
                    canvas.drawRect(manX, canvas.getHeight() / 2 + 300, manX + 30, canvas.getHeight() / 2 + 200, paint);
                    if (manX - 30 <= (canvas.getWidth() - 460)) { manX += 2;}
                    else{
                        manX = - 30;
                        manX += 2;
                    }

//                    //ДЕРЕВО
//                    // Цвет листвы
//                    paint.setColor(Color.argb(255, 30, 255, 80));
//                    // Листва дерева
//                    canvas.drawCircle(canvas.getWidth() / 2 + 300, canvas.getHeight() / 2 - 150, 150, paint);
//                    // Ствол дерева
//                    paint.setColor(Color.rgb(101, 67, 33));
//                    // левая верхняя точка, нижняя правая
//                    canvas.drawRect(canvas.getWidth() / 2 + 275, canvas.getHeight() / 2 + 300, canvas.getWidth() / 2 + 325, canvas.getHeight() / 2, paint);


//                    // ДОМ
//                    paint.setColor(Color.rgb(150, 75, 0));
//                    canvas.drawRect(canvas.getWidth() / 2 - 400, canvas.getHeight() / 2 + 300, canvas.getWidth() / 2, canvas.getHeight() / 2, paint);
//
//                    // Основание дома
//                    paint.setColor(Color.rgb(91, 57, 23));
//                    canvas.drawRect(canvas.getWidth() / 2 - 500, canvas.getHeight() / 2 + 275, canvas.getWidth() / 2 + 100, canvas.getHeight() / 2 + 300, paint);
//
//                    // Дверь
//                    paint.setColor(Color.rgb(101, 67, 33));
//                    canvas.drawRect(canvas.getWidth() / 2 - 240, canvas.getHeight() / 2 + 275, canvas.getWidth() / 2 - 160, canvas.getHeight() / 2 + 140, paint);
//
//                    // Крыша
//                    paint.setColor(Color.rgb(101, 67, 33));
//                    Point a = new Point(canvas.getWidth() / 2 - 450, canvas.getHeight() / 2);
//                    Point b = new Point((canvas.getWidth() / 2 + 150) / 2, canvas.getHeight() / 2 - 250);
//                    Point c = new Point(canvas.getWidth() / 2 + 50, canvas.getHeight() / 2);
//
//                    path.setFillType(Path.FillType.EVEN_ODD);
//
//                    path.lineTo(a.x, a.y);
//                    path.lineTo(b.x, b.y);
//                    path.lineTo(c.x, c.y);
//                    path.lineTo(a.x, a.y);
//                    path.close();

//                    canvas.drawPath(path, paint);

                    //Надпись на доме
//                    paint.setColor(Color.rgb(150, 75, 0));
//                    paint.setTextSize(30);
//                    canvas.drawText("Дом Гарника, не ломайте пж", canvas.getWidth() / 2 - 400, canvas.getHeight() / 2 - 15, paint);

                } finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }

            }
        }
    }

}
