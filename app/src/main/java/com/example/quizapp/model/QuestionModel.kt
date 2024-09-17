package com.example.quizapp.model

data class QuestionModel(
    val question : String,
    val options : List<String>,
    val correct : String
){
    constructor() : this ("", emptyList(), "")
}