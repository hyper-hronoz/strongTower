package com.example.strongtower.drawBattlefield;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Sun {
    private static boolean is_moon = false;
    private static int smileX = 0;
    private static int smileY = 400;

    public Sun(Canvas canvas, Paint paint) {
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

        // Движение по оси Y
        if (smileX <= canvas.getWidth() / 2) smileY -= 1;
        if (smileX > canvas.getWidth() / 2) smileY += 1;

        // Движение по оси X
        if (smileX - 200 <= canvas.getWidth()) smileX += 1;
        if (smileX - 200 > canvas.getWidth()) {
            smileX = -200;
            smileX += 1;
            is_moon = !(is_moon);
        }
    }
}
