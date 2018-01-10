package com.example.administrator.kotlinapp.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.administrator.kotlinapp.R
import kotlinx.android.synthetic.main.activity_circle_view.*

class CircleViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_circle_view)
        circleview.setProgressNum(5000)
        circleview_100.setProgressNum(5000)
    }
}
