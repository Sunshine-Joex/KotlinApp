package com.example.administrator.kotlinapp.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.kotlinapp.R;

/**
 * Created by Administrator on 2018/1/12.
 */

public class CircleHeadView extends View {

    private Paint mPaint;
    private Bitmap mBitmap;
    private BitmapShader mBitmapShader;

    public CircleHeadView(Context context, AttributeSet attrs) throws Exception {
        super(context, attrs);
        init();
    }

    public CircleHeadView(Context context, AttributeSet attrs, int defStyle) throws Exception {
        super(context, attrs, defStyle);
        init();
    }

    private void init() throws Exception {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.girl);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measurewidth = 300;
        int measureheight = 300;
        measurewidth = resolveSize(measurewidth, widthMeasureSpec);
        measureheight = resolveSize(measureheight, heightMeasureSpec);
        setMeasuredDimension(measurewidth, measureheight);

    }

    /*@Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int min = Math.min(getWidth(), getHeight());
        float scaleX = (float) min / mBitmap.getWidth();
        float scaleY = (float) min / mBitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.setScale(scaleX, scaleY);//根据控件宽高缩放图片
        mBitmapShader.setLocalMatrix(matrix);
        mPaint.setShader(mBitmapShader);
        canvas.drawCircle(min / 2, min / 2, min / 2, mPaint);


    }*/
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int min = Math.min(getWidth(), getHeight());
        float scaleX = (float) min / mBitmap.getWidth();
        float scaleY = (float) min / mBitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.setScale(scaleX, scaleY);//根据控件宽高缩放图片
        mBitmapShader.setLocalMatrix(matrix);
        mPaint.setShader(mBitmapShader);
//        canvas.drawCircle(min / 2, min / 2, min / 2, mPaint);
        RectF rectF=new RectF(0.0f,0.0f,getWidth(),getWidth());
        canvas.drawRoundRect(rectF,20,20,mPaint);
    }


}
