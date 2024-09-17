package com.example.quizapp.model

data class QuizModel(
    val id: String,
    val title: String,
    val subtitle: String,
    val time : String,
    val questionsList : List<QuestionModel>
){
    constructor() : this ("", "", "", "", emptyList())
}



