package com.brandon.mbti_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import java.util.Locale
/**
 * Activity to display the result of the MBTI test.
 */
class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // results looks like [1,1,2,1]
        // Retrieve the results from the intent
        val results = intent.getIntegerArrayListExtra("results") ?: arrayListOf()
        Log.d("ResultActivity-intent", "$results")

        // Define MBTI result types
        val resultTypes = listOf(
            listOf("E", "I"),
            listOf("N", "S"),
            listOf("T", "F"),
            listOf("J", "P"),
        )

        var resultString = ""

        // Convert numeric results to corresponding MBTI characters
        for (i in results.indices) {
            resultString += resultTypes[i][results[i] - 1]
        }

        val tvResValue: TextView = findViewById(R.id.tv_resValue)
        tvResValue.text = resultString


        val ivResImg: ImageView = findViewById(R.id.iv_resImg)
        val imageResource = resources.getIdentifier(
            "ic_${resultString.lowercase(Locale.ROOT)}", "drawable", packageName
        )

        ivResImg.setImageResource(imageResource)

        // Set up the retry button to restart the test
        val btnRetry: Button = findViewById(R.id.btn_res_retry)
        btnRetry.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            // flags below are actually hex code so we can use "or" operator here
            // Use flags to clear the task and start a new task
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }
}