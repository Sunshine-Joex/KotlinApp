package com.example.administrator.kotlinapp.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.administrator.kotlinapp.R;

/**
 * Created by Administrator on 2018/2/6.
 */

public class ScratchCardView extends View {
    Bitmap bgBitmap, fbBitmap;
    Paint paint;
    Canvas mCanvas;
    Path path;


    public ScratchCardView(Context context) {
        this(context, null);
    }

    public ScratchCardView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        init();
    }


    public ScratchCardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private void init() {

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setAlpha(0);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeWidth(50);
        paint.setStrokeCap(Paint.Cap.ROUND);
        path=new Path();
        bgBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.gifted);
        fbBitmap = Bitmap.createBitmap(bgBitmap.getWidth(), bgBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(fbBitmap);
        mCanvas.drawColor(Color.GRAY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.reset();
                path.moveTo(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(event.getX(), event.getY());
                break;

        }

        mCanvas.drawPath(path, paint);
        invalidate();
        return true;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bgBitmap,0,0,null);
        canvas.drawBitmap(fbBitmap,0,0,null);

    }
}
