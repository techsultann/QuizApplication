package com.techsultan.quizapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import com.techsultan.quizapplication.databinding.ActivityMainBinding
import com.techsultan.quizapplication.repository.QuizRepository
import com.techsultan.quizapplication.viewmodel.QuizViewModel
import com.techsultan.quizapplication.viewmodel.QuizViewModelFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: QuizViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        runBlocking {
            installSplashScreen()
            delay(3000)
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val repository = QuizRepository()
        val viewModelFactory = QuizViewModelFactory(application, repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[QuizViewModel::class.java]
    }
}