package com.example.strongtower.drawBattlefield;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class Castle {
    public int hitPoint = 10;

    public final double Y_POSITION = 934;
    public final double X_POSITION = 100;

    public Castle(Canvas canvas, Paint paint) {
        paint.setColor(Color.rgb(91, 57, 23));
        canvas.drawRect(canvas.getWidth() - 400, canvas.getHeight() / 2 + 300, canvas.getWidth(), canvas.getHeight() / 2, paint);
    }
}
