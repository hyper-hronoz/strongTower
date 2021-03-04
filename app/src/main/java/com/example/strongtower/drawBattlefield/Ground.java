package com.example.strongtower.drawBattlefield;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Ground {

    public Ground(Canvas canvas, Paint paint) {
        paint.setColor(Color.rgb(33, 135, 11));
        canvas.drawRect(0, canvas.getHeight() / 2 + 300, canvas.getWidth(), canvas.getHeight(), paint);
    }
}
