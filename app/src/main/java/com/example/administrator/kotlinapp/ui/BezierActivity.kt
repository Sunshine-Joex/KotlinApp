package com.example.administrator.kotlinapp.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.administrator.kotlinapp.R
import kotlinx.android.synthetic.main.activity_bezier.*

class BezierActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bezier)
        magicCircle.startAnimation()
    }
}
