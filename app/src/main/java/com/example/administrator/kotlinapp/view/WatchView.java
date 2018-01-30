package com.example.administrator.kotlinapp.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Calendar;

/**
 * Created by Administrator on 2018/1/29.
 */

public class WatchView extends View {
    //依次是外圆画笔，刻度画笔，时针画笔，
    Paint mCirclePaint, mDegreePaint, mHourPaint, mMinutePaint,mSecondPaint;
    //获取View的宽、高、以及圆半径
    int mWidth, mHeight, mRadius;
    Calendar calendar;
    public WatchView(Context context) {
        this(context, null);
    }
    public WatchView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        init();
    }
    public WatchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {

        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setStrokeWidth(5);
        mCirclePaint.setColor(Color.WHITE);

        mDegreePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDegreePaint.setStyle(Paint.Style.STROKE);
        mDegreePaint.setColor(Color.WHITE);
        mDegreePaint.setStrokeWidth(3);

        mHourPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mHourPaint.setColor(Color.WHITE);
        mHourPaint.setStrokeWidth(8);

        mMinutePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mMinutePaint.setColor(Color.WHITE);
        mMinutePaint.setStrokeWidth(4);

        mSecondPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSecondPaint.setColor(Color.WHITE);
        mSecondPaint.setStrokeWidth(2);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //这里获取宽高，我们取宽和高的最小值作为外圆的半径
        mWidth = w;
        mHeight = h;
        mRadius = Math.min(w, h)/2;
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measurewidth = 400;
        int measureheight = 400;
        measurewidth = resolveSize(measurewidth, widthMeasureSpec);
        measureheight = resolveSize(measureheight, heightMeasureSpec);
        setMeasuredDimension(measurewidth, measureheight);

    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        calendar = Calendar.getInstance();
//-------------------------------------绘制最外层圆---------------------------------------------------------------
        canvas.drawCircle(mWidth / 2, mHeight / 2, mRadius , mCirclePaint);

//-------------------------------------绘制刻度和刻度值---------------------------------------------------------------
        for (int i = 0; i < 60; i++) {
            //被5整除的i恰好对应于我们的粗体刻度，也就是：12,1,2,3......11
            if (i % 5 == 0) {
                mDegreePaint.setStrokeWidth(5);
                mDegreePaint.setTextSize(30);
                //如图：A点坐标（mWidth / 2, mHeight / 2 - mWidth / 2） B点（mWidth / 2, mHeight / 2 - mWidth / 2 + 60, mDegreePaint）
                canvas.drawLine(mWidth / 2, mHeight / 2 - mWidth / 2, mWidth / 2, mHeight / 2 - mWidth / 2 + 60, mDegreePaint);
                String num = String.valueOf(i / 5);
                if ("0".equals(num)) {
                    num = "12";
                }
                //绘制时间数值，X坐标：半径-数值文本宽度一半    Y坐标：90
                canvas.drawText(num, mWidth / 2 - mDegreePaint.measureText(num) / 2, mHeight / 2 - mWidth / 2 + 90, mDegreePaint);
            } else {
                mDegreePaint.setStrokeWidth(3);
//                mDegreePaint.setTextSize(15);
                //小刻度类似粗体刻度，我们看下坐标几乎和刚才粗体刻度一样，只有终止坐标Y轴是+30，而粗体坐标Y轴是+60，也就是比粗体刻度短30
                canvas.drawLine(mWidth / 2, mHeight / 2 - mWidth / 2, mWidth / 2, mHeight / 2 - mWidth / 2 + 30, mDegreePaint);
//                String num = String.valueOf(i / 5);
            }
          //画布旋转操作：一圈360度，共有60个刻度格，每个6度，所以每次旋转6度，也就是下面方法的第一个参数，后面两个参数其实就是旋转中心
            canvas.rotate(6, mWidth / 2, mHeight / 2);
        }
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        canvas.save();//这个方法就是保存画布，也就是把上面的操作保存起来，然后后续的操作就像新画布一样
//        canvas.translate(mWidth/2,mHeight/2);
        canvas.rotate((hour * 5*6)+(minute*0.5f),mWidth/2,mHeight/2);
        canvas.drawLine(mWidth/2,mHeight/2, mWidth / 2, mHeight / 2 - mWidth / 2 + 250, mHourPaint);
        canvas.restore();//save()前和save()后的画布做合并

        canvas.save();
        canvas.rotate(minute * 6,mWidth/2,mHeight/2);
        canvas.drawLine(mWidth/2,mHeight/2, mWidth / 2, mHeight / 2 - mWidth / 2 + 150, mMinutePaint);
        canvas.restore();

        canvas.save();
        canvas.rotate(second * 6,mWidth/2,mHeight/2);
        canvas.drawLine(mWidth/2,mHeight/2, mWidth / 2, mHeight / 2 - mWidth / 2 + 100, mSecondPaint);
        canvas.restore();

        postInvalidateDelayed(1000);
        Log.i("refresh", "onDraw: ------"+second);
    }


}
