package com.techsultan.quizapplication.model

data class Question(

    val questionText: String,
    val options : List<String>,
    val correctOption : Int
)
