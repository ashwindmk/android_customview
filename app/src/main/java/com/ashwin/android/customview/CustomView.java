package com.ashwin.android.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

public class CustomView extends View {
    private Rect rectangle;
    private Paint paint;

    public CustomView(Context context) {
        super(context);

        int top = 25;
        int left = 50;
        int sideLength = 200;

        // Create a rectangle that we will draw later
        rectangle = new Rect(left, top, sideLength, sideLength);

        // Create the Paint and set its color
        paint = new Paint();
        paint.setColor(Color.GRAY);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d("custom-view", "View: on-attached-to-window");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d("custom-view", "View: on-measure");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d("custom-view", "View: on-draw");
        canvas.drawColor(Color.LTGRAY);
        canvas.drawRect(rectangle, paint);
    }
}
