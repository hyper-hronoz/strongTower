package com.example.strongtower.drawBattlefield;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class Castle {
    public int hitPoint = 10;
    private Canvas canvas;
    private Paint paint = new Paint();
    private int castleHeight ;

    public final double Y_POSITION = 934;
    public final double X_POSITION = 300;

    public Castle(Canvas canvas) {
        this.canvas = canvas;
        this.castleHeight = canvas.getHeight() + 300;

        draw();
    }

    private void draw() {
        paint.setColor(Color.rgb(91, 57, 23));
        canvas.drawRect(canvas.getWidth() - 400, castleHeight, canvas.getWidth(), canvas.getHeight() / 2, paint);
    }
}
