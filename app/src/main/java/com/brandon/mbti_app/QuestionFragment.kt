package com.brandon.mbti_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

/**
 * A fragment representing a question in the questionnaire.
 */
class QuestionFragment : Fragment() {

    // Current question page number
    private var questionPage: Int = 0

    // List of question categories
    private val questionCategory = listOf(
        R.string.question1_title,
        R.string.question2_title,
        R.string.question3_title,
        R.string.question4_title,
    )

    // List of question titles
    private val questionTitle = listOf(
        listOf(R.string.question1_1, R.string.question1_2, R.string.question1_3),
        listOf(R.string.question2_1, R.string.question2_2, R.string.question2_3),
        listOf(R.string.question3_1, R.string.question3_2, R.string.question3_3),
        listOf(R.string.question4_1, R.string.question4_2, R.string.question4_3),
    )

    // List of question answers
    private val questionAnswers = listOf(
        listOf(
            listOf(R.string.question1_1_answer1, R.string.question1_1_answer2),
            listOf(R.string.question1_2_answer1, R.string.question1_2_answer2),
            listOf(R.string.question1_3_answer1, R.string.question1_3_answer2),
        ),
        listOf(
            listOf(R.string.question2_1_answer1, R.string.question2_1_answer2),
            listOf(R.string.question2_2_answer1, R.string.question2_2_answer2),
            listOf(R.string.question2_3_answer1, R.string.question2_3_answer2),
        ),
        listOf(
            listOf(R.string.question3_1_answer1, R.string.question3_1_answer2),
            listOf(R.string.question3_2_answer1, R.string.question3_2_answer2),
            listOf(R.string.question3_3_answer1, R.string.question3_3_answer2),
        ),
        listOf(
            listOf(R.string.question4_1_answer1, R.string.question4_1_answer2),
            listOf(R.string.question4_2_answer1, R.string.question4_2_answer2),
            listOf(R.string.question4_3_answer1, R.string.question4_3_answer2),
        ),
    )

    companion object {
        private const val ARG_QUESTION_TYPE = "questionType"

        /**
         * Create a new instance of QuestionFragment.
         * @param questionPage The page number of the question.
         * @return The newly created QuestionFragment instance.
         */
        fun nextInstance(questionPage: Int): QuestionFragment {
            val fragment = QuestionFragment()
            val args = Bundle()
            args.putInt(ARG_QUESTION_TYPE, questionPage)
            fragment.arguments = args
            return fragment
        }
    }

    // 1. Retrieve the data from Fragment arguments
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            questionPage = it.getInt(ARG_QUESTION_TYPE)
        }
    }

    // Inflate the fragment layout, get view references, set up data on the views, and return the view
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_question, container, false)

        val title: TextView = view.findViewById(R.id.tvQuestionTitle)
        title.text = getString(questionCategory[questionPage])

        val questionTitleViews = listOf<TextView>(
            view.findViewById(R.id.tvQuestion1),
            view.findViewById(R.id.tvQuestion2),
            view.findViewById(R.id.tvQuestion3),
        )

        val answerRadioGroupViews = listOf<RadioGroup>(
            view.findViewById(R.id.rgAnswer1),
            view.findViewById(R.id.rgAnswer2),
            view.findViewById(R.id.rgAnswer3),
        )

        for (i in questionTitleViews.indices) {
            questionTitleViews[i].text = getString(questionTitle[questionPage][i])
            val radioButton1 = answerRadioGroupViews[i].getChildAt(0) as RadioButton
            val radioButton2 = answerRadioGroupViews[i].getChildAt(1) as RadioButton

            radioButton1.text = getString(questionAnswers[questionPage][i][0])
            radioButton2.text = getString(questionAnswers[questionPage][i][1])
        }
        return view
    }

    // Define the functionality of the nextButton
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val answerRadioGroupViews = listOf<RadioGroup>(
            view.findViewById(R.id.rgAnswer1),
            view.findViewById(R.id.rgAnswer2),
            view.findViewById(R.id.rgAnswer3),
        )

        val btnNext: Button = view.findViewById(R.id.btnNext)
        btnNext.setOnClickListener {
            // Check if any option is selected for each question
            val isAllAnswered = answerRadioGroupViews.all {
                it.checkedRadioButtonId != -1
            }
            // If all questions are answered
            if (isAllAnswered) {
                val response = answerRadioGroupViews.map { radioGroup ->
                    val firstRadioButton = radioGroup.getChildAt(0) as RadioButton
                    if (firstRadioButton.isChecked) 1 else 2
                }

                (activity as TestActivity)?.questionnaireResults?.addResponse(response)
                (activity as? TestActivity)?.moveToNextQuestion()

            } else {
                Toast.makeText(context, "모든 질문에 답해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }

}