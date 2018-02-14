package com.example.administrator.kotlinapp.touchevent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import com.example.administrator.kotlinapp.R;

public class TouchEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_event);
    }




    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i("test", "Activity:dispatchTouchEvent: "+ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("test", "Activity:onTouchEvent: "+event.getAction());
        return super.onTouchEvent(event);
    }




}
