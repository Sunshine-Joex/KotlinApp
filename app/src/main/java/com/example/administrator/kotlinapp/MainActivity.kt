package com.example.administrator.kotlinapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.administrator.kotlinapp.ui.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() ,View.OnClickListener{


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        circleView.setOnClickListener(this)
        test.setOnClickListener(this)
        path.setOnClickListener(this)
        bezier.setOnClickListener(this)
        shader.setOnClickListener(this)
        watch.setOnClickListener(this)
        porterDuffMode.setOnClickListener(this)
        porterDuffXfermode.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
           R.id.circleView ->   startActivity(Intent(this, CircleViewActivity::class.java))
           R.id.test ->startActivity(Intent(this, PieChartActivity::class.java))
            R.id.path->startActivity(Intent(this,RadarChartActivity::class.java))
            R.id.bezier ->startActivity(Intent(this,BezierActivity::class.java))
            R.id.shader ->startActivity(Intent(this,ShaderActivity::class.java))
            R.id.watch ->startActivity(Intent(this,WatchActivity::class.java))
            R.id.porterDuffMode ->startActivity(Intent(this,PorterDuffModeActivity::class.java))
            R.id.porterDuffXfermode ->startActivity(Intent(this,PorterDuffXfermodeActivity::class.java))


        }
    }



}