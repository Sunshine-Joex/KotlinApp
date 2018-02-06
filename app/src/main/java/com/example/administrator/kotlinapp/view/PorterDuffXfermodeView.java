package com.example.administrator.kotlinapp.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;


import com.example.administrator.kotlinapp.R;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2018/1/30.
 */

@SuppressLint("AppCompatCustomView")
public class PorterDuffXfermodeView extends ImageView {


    private static final Xfermode sXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
    private Bitmap mMaskBitmap;
    private Paint mPaint;
    int type;//绘制形状
    public static final int CIRCLE = 0;//原型
    public static final int ROUNDRECT = 1;//圆角
    public static final int HEART = 2;//心形
    public static final int OVAL = 3;//椭圆

    public PorterDuffXfermodeView(Context context) {
        super(context);
        init();
    }

    public PorterDuffXfermodeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        TypedArray array = context.obtainStyledAttributes(attrs,
                R.styleable.pdxView);
        type = array.getInt(R.styleable.pdxView_type, 0);// 默认为Circle
        array.recycle();
    }

    public PorterDuffXfermodeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        Bitmap bitmap = null;
        Drawable drawable = getDrawable();
        if (drawable != null) {
            bitmap = Bitmap.createBitmap(getWidth(),
                    getHeight(), Bitmap.Config.ARGB_8888);
            Canvas bitmapCanvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, getWidth(), getHeight());
            drawable.draw(bitmapCanvas);

            if (mMaskBitmap == null || mMaskBitmap.isRecycled()) {
                switch (type) {
                    case CIRCLE:
                        mMaskBitmap = getBitmapCircle(getWidth(), getHeight());
                        break;
                    case ROUNDRECT:
                        mMaskBitmap = getBitmapRoundRect(getWidth(), getHeight());
                        break;
                    case HEART:
                        mMaskBitmap = getBitmapHeart(getWidth(), getHeight());
                        break;
                    case OVAL:
                        mMaskBitmap = getBitmapOval(getWidth(), getHeight());
                        break;

                }

            }

            mPaint.setXfermode(sXfermode);
            bitmapCanvas.drawBitmap(mMaskBitmap, 0.0f, 0.0f, mPaint);

            mPaint.setXfermode(null);
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, mPaint);
        }
    }

    public static Bitmap getBitmapRoundRect(int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        canvas.drawRoundRect(new RectF(0.0f, 0.0f, width, height), 50, 50, paint);

        return bitmap;
    }

    public static Bitmap getBitmapCircle(int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        canvas.drawCircle(width / 2, height / 2, Math.min(width / 2, height / 2), paint);
        return bitmap;
    }

    public static Bitmap getBitmapHeart(int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        Path path = new Path();
        path.addArc(200, 200, 400, 400, -225, 225);
        path.arcTo(400, 200, 600, 400, -180, 225, false);
        path.lineTo(400, 542);
        canvas.drawPath(path, paint);
        return bitmap;
    }

    public static Bitmap getBitmapOval(int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        canvas.drawOval(new RectF(0.0f, 0.0f, width, height), paint);
        return bitmap;
    }


}
