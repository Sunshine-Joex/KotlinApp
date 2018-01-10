package com.example.administrator.kotlinapp.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2018/1/10.
 */

public class RadarChartView extends View {
    Paint mPaint,textPaint,zonePaint;
    private int centerX;                  //中心X
    private int centerY;                  //中心Y
    private float radius;                   //网格最大半径
    private int count = 6;                //数据个数

    private String[] titles = {"李白","亚瑟","妲己","甄姬","赵云","韩信"};
    private double[] data = {100,60,60,60,100,50,10,20}; //各维度分值
    private float maxValue = 100;             //数据最大值
    private float angle = (float) (Math.PI*2/count);
    public RadarChartView(Context context) {
        super(context);
    }

    public RadarChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.GRAY);

        textPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
         textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(Color.RED);
        textPaint.setTextSize(36);

        zonePaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        zonePaint.setStyle(Paint.Style.FILL);
        zonePaint.setColor(Color.BLUE);
    }

    public RadarChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        radius = Math.min(h, w)/2*0.9f;
        //中心坐标
        centerX = w/2;
        centerY = h/2;
        postInvalidate();
        super.onSizeChanged(w, h, oldw, oldh);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawPolygon(canvas);
        drawLines(canvas);
        drawText( canvas);
        drawRegion(canvas);

    }
    private void drawPolygon(Canvas canvas){
        Path path = new Path();
        float r = radius/(count-1);//r是蜘蛛丝之间的间距
        for(int i=1;i<count;i++){//中心点不用绘制
            float curR = r*i;//当前半径
            path.reset();// todo
            for(int j=0;j<count;j++){
                if(j==0){
                    path.moveTo(centerX+curR,centerY);
                }else{
                    //根据半径，计算出蜘蛛丝上每个点的坐标
                    float x = (float) (centerX+curR*Math.cos(angle*j));
                    float y = (float) (centerY+curR*Math.sin(angle*j));
                    path.lineTo(x,y);
                }
            }
            path.close();//闭合路径
            canvas.drawPath(path, mPaint);
        }



    }
    private void drawLines(Canvas canvas){
        Path path = new Path();
        for(int i=0;i<count;i++){
            path.reset();
            path.moveTo(centerX, centerY);
            float x = (float) (centerX+radius*Math.cos(angle*i));
            float y = (float) (centerY+radius*Math.sin(angle*i));
            path.lineTo(x, y);
            canvas.drawPath(path, mPaint);
        }
    }

    private void drawText(Canvas canvas){
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float fontHeight = fontMetrics.descent - fontMetrics.ascent;
        for(int i=0;i<count;i++){
            float x = (float) (centerX+(radius+fontHeight/2)*Math.cos(angle*i));
            float y = (float) (centerY+(radius+fontHeight/2)*Math.sin(angle*i));
            if(angle*i>=0&&angle*i<=Math.PI/2){//第4象限
                canvas.drawText(titles[i], x,y,textPaint);
            }else if(angle*i>=3*Math.PI/2&&angle*i<=Math.PI*2){//第3象限
                canvas.drawText(titles[i], x,y,textPaint);
            }else if(angle*i>Math.PI/2&&angle*i<=Math.PI){//第2象限
                float dis = textPaint.measureText(titles[i]);//文本长度
                canvas.drawText(titles[i], x-dis,y,textPaint);
            }else if(angle*i>=Math.PI&&angle*i<3*Math.PI/2){//第1象限
                float dis = textPaint.measureText(titles[i]);//文本长度
                canvas.drawText(titles[i], x-dis,y,textPaint);
            }
        }
    }


    private void drawRegion(Canvas canvas){
        Path path = new Path();
        zonePaint.setAlpha(255);
        for(int i=0;i<count;i++){
            double percent = data[i]/maxValue;
            float x = (float) (centerX+radius*Math.cos(angle*i)*percent);
            float y = (float) (centerY+radius*Math.sin(angle*i)*percent);
            if(i==0){
                path.moveTo(x, centerY);
            }else{
                path.lineTo(x,y);
            }
            //绘制小圆点
            canvas.drawCircle(x,y,10,zonePaint);
        }
        zonePaint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, zonePaint);
        zonePaint.setAlpha(127);
        //绘制填充区域
        zonePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawPath(path, zonePaint);
    }

}
