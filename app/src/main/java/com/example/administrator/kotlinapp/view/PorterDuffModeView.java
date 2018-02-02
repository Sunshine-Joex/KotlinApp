package com.example.administrator.kotlinapp.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2018/2/1.
 */

public class PorterDuffModeView extends View {
    Paint paint;
    public PorterDuffModeView(Context context) {
//        super(context);
        this(context, null);
    }

    public PorterDuffModeView(Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
        this(context, attrs, 0);
        paint = new Paint();
        //几种混合规则在GPU硬件加速下不起效，如果你觉得混合模式没有正确使用，
        // 可以让调用View.setLayerType(View.LAYER_TYPE_SOFTWARE, null)方法
//        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    public PorterDuffModeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //离屏缓存
        canvas.drawColor(Color.GRAY);
      int sc=  canvas.saveLayer(0f, 0f, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(getBitmapDest(getWidth(),getHeight()), 0, 0, paint);
         paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.OVERLAY));
        canvas.drawBitmap(getBitmapSrc(getWidth(),getHeight()), 0, 0, paint);

        // 还原混合模式
        paint.setXfermode(null);

        // 还原画布
        canvas.restoreToCount(sc);
    }

    /**
     *  目标图像 红色圆
     * @param width
     * @param height
     * @return
     */
   public static Bitmap getBitmapDest(int width, int height) {

        Bitmap bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.parseColor("#E91E63"));
        canvas.drawCircle(width/2 , height / 2, Math.min(width / 4, height / 4), paint);
        return bitmap;
    }

    /**
     *  目标图像 蓝色矩形
     * @param width
     * @param height
     * @return
     */
    public static Bitmap getBitmapSrc(int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.parseColor("#2196F3"));
        canvas.drawRect(new RectF(0,0.5f*height,0.5f*width,height),paint);
        return bitmap;
    }




}
