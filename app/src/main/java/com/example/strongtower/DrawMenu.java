package com.example.strongtower;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.strongtower.R;
import com.example.strongtower.MainActivity;
import com.example.strongtower.drawBattlefield.DrawView;

public class DrawMenu extends SurfaceView implements SurfaceHolder.Callback{

    private int xTouch;
    private int yTouch;
    private Intent intent;

    private DrawThread drawThread; // поле для хранения экземпляра потока

    private int draw_butt_up_X, draw_frst_butt_up_Y, draw_butt_down_X, draw_frst_butt_down_Y, draw_sec_butt_up_Y, draw_sec_butt_down_Y, draw_butt_heigh;

    public DrawMenu(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // тут можно что-то делать по касаниям
        xTouch = (int)event.getX(); // координаты точки касания
        yTouch = (int)event.getY();

        if (((xTouch >= draw_butt_up_X) && (xTouch <= draw_butt_down_X)) && ((yTouch >= draw_frst_butt_up_Y) && (yTouch <= draw_frst_butt_down_Y))){ // первая кнопка
            intent = new Intent(getContext(), MainActivity.class);
            getContext().startActivity(intent);
        }
        if (((xTouch >= draw_butt_up_X) && (xTouch <= draw_butt_down_X)) && ((yTouch >= draw_sec_butt_up_Y) && (yTouch <= draw_sec_butt_down_Y))){ // вторая кнопка
            System.out.println("Вторая");
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        drawThread = new DrawThread(getContext(),getHolder()); // создаем экземпляр потока, передаем туда контекст и холдер
        drawThread.start();    // стартуем поток(запускаем метод run)
        int index = 0;
        while (drawThread.running == true){ // задержка
            index += 1;
        }

        draw_butt_heigh = drawThread.getButt_heigh(); // canvas.getHeight() / 6;

        draw_butt_up_X = drawThread.getButt_up_X(); // canvas.getWidth() / 4;
        draw_frst_butt_up_Y = drawThread.getFrst_Butt_up_Y(); // canvas.getHeight() / 4 * 2;

        draw_butt_down_X = drawThread.getButt_down_X(); // butt_up_X * 3;
        draw_frst_butt_down_Y = drawThread.getFrst_Butt_down_Y(); // canvas.getHeight() / 4 * 2 + canvas.getHeight() / 6;

        draw_sec_butt_up_Y = drawThread.getSec_butt_up_Y(); // canvas.getHeight() / 3 * 2 + 30;
        draw_sec_butt_down_Y = drawThread.getSec_butt_down_Y(); // canvas.getHeight() / 3 * 2 + canvas.getHeight() / 6 + 30;
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        drawThread.requestStop();  // кидаем запрос на остановку потока
        boolean retry = true;  // поднимаем флаг повтора попытки остановки
        while (retry) {     // циклим по флагу
            try {   // пробуем остановить поток
                drawThread.join();  // попытка
                retry = false;  // если на предыдущем шаге не вылетает исключение, то опускаем флаг
            } catch (InterruptedException e) {
                //ловим исключение
            }
        }
    }
}

class DrawThread extends Thread {

    private int butt_up_X, frst_butt_up_Y, butt_down_X, frst_butt_down_Y, sec_butt_up_Y, sec_butt_down_Y, butt_heigh; // положение кнопок

    public Context context;

    public Bitmap bitmap;

    public volatile boolean running = true;    // флаг для остановки потока

    public SurfaceHolder surfaceHolder;    // приватное поле для держателя канваса

    public int getButt_up_X(){
        return butt_up_X;
    }

    public int getFrst_Butt_up_Y() {
        return frst_butt_up_Y;
    }

    public int getButt_down_X() {
        return butt_down_X;
    }

    public int getFrst_Butt_down_Y(){
        return frst_butt_down_Y;
    }

    public int getSec_butt_up_Y(){
        return sec_butt_up_Y;
    }

    public int getSec_butt_down_Y(){
        return sec_butt_down_Y;
    }

    public int getButt_heigh(){
        return butt_heigh;
    }

    public DrawThread(Context context, SurfaceHolder surfaceHolder) { // конструктор
        this.context = context;
        this.surfaceHolder = surfaceHolder;
    }

    public void requestStop() {         // метод, которым мы останавливаем поток
        running = false;
    }

    @Override
    public void run() {
        Canvas canvas = surfaceHolder.lockCanvas();     // "захватываем" канвас, блокируем его, чтобы не было проблем.
        butt_heigh = canvas.getHeight() / 6;

        butt_up_X = canvas.getWidth() / 4;
        frst_butt_up_Y = canvas.getHeight() / 4 * 2;

        butt_down_X = butt_up_X * 3;
        frst_butt_down_Y = frst_butt_up_Y + butt_heigh;

        sec_butt_up_Y = canvas.getHeight() / 4 * 3;
        sec_butt_down_Y = sec_butt_up_Y + butt_heigh;
        Paint paint = new Paint();

        while (running) {              // зацикливаем, пока флаг работы поднят. Как только получаем запрос на остановку, выхоим из этого цикла
            if (canvas != null) {       // если захватить канвас получилось, и метод "lockCanvas()" вернул ссылку, а не "null", тогда пробуем рисовать
                try {
                    canvas.drawRGB(0,191,255);
                    paint.setSubpixelText(true);
                    paint.setAntiAlias(true);
                    paint.setStyle(Paint.Style.FILL);
                    paint.setColor(Color.GRAY);

                    canvas.drawRect(butt_up_X, frst_butt_up_Y, butt_down_X, frst_butt_down_Y, paint);
                    canvas.drawRect(butt_up_X, sec_butt_up_Y, butt_down_X, sec_butt_down_Y, paint);

                    bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.logo2);
                    Rect destination = new Rect(butt_up_X - 100, 0, butt_down_X + 100, frst_butt_up_Y / 2 + butt_heigh);
                    canvas.drawBitmap ( bitmap, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), destination, paint );

                    requestStop();
                } finally { // catch можно пропустить, тк выводить ничего не надо, да и делать, собственно, тоже
                    surfaceHolder.unlockCanvasAndPost(canvas);  // в блоке finally обязательно разблокируем и постим канвас обратно
                }
            }
        }
    }
}

