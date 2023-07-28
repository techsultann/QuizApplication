package com.techsultan.quizapplication.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.techsultan.quizapplication.repository.QuizRepository

class QuizViewModelFactory(
    private val app: Application,
    private val repository: QuizRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return QuizViewModel(app, repository) as T
    }
}