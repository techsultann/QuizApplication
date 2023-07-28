package com.techsultan.quizapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.techsultan.quizapplication.model.Question
import com.techsultan.quizapplication.repository.QuizRepository
import kotlin.random.Random

class QuizViewModel(
    application: Application,
    private val repository: QuizRepository
) : AndroidViewModel(application) {

    val availableQuestions: MutableList<Question> = mutableListOf()

    private val _currentQuestionIndex = MutableLiveData(0)
    val currentQuestionIndex: LiveData<Int>
        get() = _currentQuestionIndex

    private val _score = MutableLiveData(0)
    val score: LiveData<Int>
        get() = _score

    private val _quizFinished = MutableLiveData(false)
    val quizFinished: LiveData<Boolean>
        get() = _quizFinished

    private val questions = mutableListOf(

        Question(
            "What is Android?",

            listOf("Android is a stack of software's for mobility",
                "Google mobile device name",
                "Virtual machine",
                "None of the above"),

            1
        ),

        Question(
            "What is Manifest.xml in android?",

            listOf("Initial activity of an application",
                "Initial service of an application",
                "Initial method of an application",
                "Initial screen of an application"),

            4
        ),

        Question(
            "   How to pass the data between activities in Android?",

            listOf("Intent",
                "Content Provider",
                "Broadcast receiver",
                "None of the Above"),

            1
        )
    )

   /* // Function to get a random question from the available questions list
    fun getRandomQuestion(): Question? {
        return if (availableQuestions.isEmpty()) {
            null // Return null if there are no more questions available
        } else {
            val randomIndex = Random.nextInt(0, availableQuestions.size)
            val randomQuestion = availableQuestions[randomIndex]
            availableQuestions.removeAt(randomIndex) // Remove the selected question from the list to avoid repetition
            randomQuestion
        }
    }*/

    init {
        questions.shuffle()
    }


    fun getCurrentQuestion(): Question {
        val index = currentQuestionIndex.value ?: 0
        return questions[index]
    }

    fun goToNextQuestion(): Int {;
        val nextIndex = (currentQuestionIndex.value ?: 0) + 1
        if (nextIndex < (questions.size ?: 0)) {

        }
        return questions.size
    }

    private var totalCorrectAnswers = 0

    fun checkAnswer(selectedOptionIndex: Int): Boolean {
        val currentQuestion = getCurrentQuestion()
        val isAnswerCorrect = selectedOptionIndex == currentQuestion.correctOption

        if (isAnswerCorrect) {
            totalCorrectAnswers++
        }

        return isAnswerCorrect
    }

    fun onOptionSelected(selectedIndex: Int) {
        val currentQuestion = getCurrentQuestion()
        if (currentQuestion.correctOption == selectedIndex) {
            _score.value = (_score.value ?: 0) + 1
        }

        val nextIndex = (currentQuestionIndex.value ?: 0) + 1
        if (nextIndex < (questions.size)) {
            _currentQuestionIndex.value = nextIndex
        } else {
            _quizFinished.value = true
        }
    }

    fun resetQuiz() {
        _currentQuestionIndex.value = 0
        _score.value = 0
        questions.shuffle() // Randomly shuffle the questions again for the next quiz
        _quizFinished.value = false
    }


    fun getTotalCorrectAnswers(): Int {
        return totalCorrectAnswers
    }
}