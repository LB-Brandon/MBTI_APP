package com.brandon.mbti_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    private val btnStart: ImageView by lazy { findViewById(R.id.ivStart) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart.setOnClickListener {
            val intent = Intent(this, TestActivity::class.java)
            startActivity(intent)
        }
    }
}