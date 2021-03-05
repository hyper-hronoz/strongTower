package com.example.strongtower.drawBattlefield;

import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class DrawView extends SurfaceView implements SurfaceHolder.Callback {

    private DrawBattlefield drawBattlefield;

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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        drawBattlefield.setTowardPoint((int) event.getX(), (int) event.getY());

        return false;
    }
}
