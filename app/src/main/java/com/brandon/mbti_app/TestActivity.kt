package com.brandon.mbti_app

import android.content.Intent
import android.media.tv.AdResponse
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.viewpager2.widget.ViewPager2

class TestActivity : AppCompatActivity() {

    // Lazy initialization of ViewPager2
    private val viewPager: ViewPager2 by lazy { findViewById(R.id.viewPager) }

    // QuestionnaireResults instance to store user responses
    val questionnaireResults = QuestionnaireResults()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        // Set up ViewPager2 with ViewPagerAdapter.
        // In this context, "this" refers to the current activity(TextActivity)
        viewPager.adapter = ViewPagerAdapter(this)
        viewPager.isUserInputEnabled = false  // Disable user input for swipe navigation

    }

    fun moveToNextQuestion() {
        if (viewPager.currentItem == 3) {
            // Last page reached, navigate to the ResultActivity
            val intent = Intent(this, ResultActivity::class.java)
            intent.putIntegerArrayListExtra("results", ArrayList(questionnaireResults.results))
            startActivity(intent)
        } else {
            val nextItem = viewPager.currentItem + 1
            // Move to the next question page if available
            if (nextItem < viewPager.adapter?.itemCount ?: 0) {
                viewPager.setCurrentItem(nextItem, true)
            }
        }
    }

}

// Class to store and process user questionnaire results
class QuestionnaireResults {
    // List to store user responses
    val results = mutableListOf<Int>()

    // Add user response to the results list
    fun addResponse(response: List<Int>) {
        // Determine which character is selected more frequently
        var text = ""
        val mostFrequent = response.groupingBy { it }.eachCount().maxByOrNull { it.value }?.key
        mostFrequent?.let { results.add(it) }
    }
}