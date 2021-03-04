package com.example.strongtower.drawBattlefield;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Enemy {
    private static int manX = 0;

    public Enemy(Canvas canvas, Paint paint) {
        paint.setColor(Color.rgb(41, 54, 43));
        canvas.drawRect(manX, canvas.getHeight() / 2 + 300, manX + 30, canvas.getHeight() / 2 + 200, paint);
        if (manX - 30 > (canvas.getWidth() - 460)) {
            manX = -30;
        }
        manX += 2;
    }
}
