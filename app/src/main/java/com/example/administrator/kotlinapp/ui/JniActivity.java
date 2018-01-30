package com.example.administrator.kotlinapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.administrator.kotlinapp.R;

public class JniActivity extends AppCompatActivity {

     {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jni);
        ((TextView)findViewById(R.id.textView)).setText(add(99,1)+"");
    }

    public native int add(int a,int b);
}
