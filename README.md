# MBTI Test App

<img src="https://github.com/LB-Brandon/MBTI_APP/assets/84883277/e52519cc-bf3e-46c2-91fd-f459891fa27a" width="200" height="400"/>

## Overview

The MBTI Test App is a mobile application designed for conducting the Myers-Briggs Type Indicator (MBTI) personality test. Users answer a series of questions to determine their MBTI personality type. The app provides an interactive interface with multiple-choice questions and displays the resulting MBTI type.

## Features

- **Interactive Test:** Users can answer questions presented in a user-friendly interface.
- **MBTI Result:** Displays the MBTI personality type based on the user's responses.
- **Navigation:** Users can navigate through questions and view the result.

## Code Highlights

### Fragment Initialization

```kotlin
companion object {
    private const val ARG_QUESTION_TYPE = "questionType"

    fun nextInstance(questionPage: Int): QuestionFragment {
        val fragment = QuestionFragment()
        val args = Bundle()
        args.putInt(ARG_QUESTION_TYPE, questionPage)
        fragment.arguments = args
        return fragment
    }
}
```

Creating a new instance of the `QuestionFragment` with arguments.

### UI Update on View Creation

```kotlin
override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
): View? {
    // ... (code for setting up UI elements)
    return view
}
```

Inflating the fragment layout and setting up UI elements based on the question data.

### User Input Handling

```kotlin
override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val answerRadioGroupViews = listOf<RadioGroup>(
        view.findViewById(R.id.rgAnswer1),
        view.findViewById(R.id.rgAnswer2),
        view.findViewById(R.id.rgAnswer3),
    )

    val btnNext: Button = view.findViewById(R.id.btnNext)
    btnNext.setOnClickListener {
        // ... (code for handling user input and moving to the next question)
    }
}
```

Handling user input and moving to the next question when the "Next" button is clicked.

## How to Use

1. Start the app and proceed through the questions.
2. Answer each question using the provided options.
3. Navigate through questions using the "Next" button.
4. View the MBTI result after completing all questions.

## Technologies Used

- Android Studio
- Kotlin

Feel free to contribute to the project or provide feedback!
