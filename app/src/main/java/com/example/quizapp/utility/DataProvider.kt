package com.example.quizapp.utility

import com.example.quizapp.model.QuestionModel
import com.example.quizapp.model.QuizModel

object DataProvider {

    val quizList : MutableList<QuizModel> = arrayListOf()
    val questionList : MutableList<QuestionModel> = arrayListOf()

    private fun addQuiz(id: String, title: String, subtitle : String, time : String, questions : List<QuestionModel>){
        val quiz = QuizModel(id, title, subtitle,time, questions)
        quizList.add(quiz)
    }


    private fun addQuestion(question : String, options : List<String>, correct : String){
        val questionItem = QuestionModel(question, options, correct)
        questionList.add(questionItem)
    }

    init {
        addQuiz("1", "Android", "All about android os", "10", questionList)
        addQuiz("2", "Windows", "All about windows os", "15", questionList)
        addQuiz("3", "GK", "All about General Knowledge", "20", questionList)
        addQuestion("What is Android", listOf("OS", "Programming Language", "Protocol", "None"), "OS")
        addQuestion("What id Kotlin", listOf("OS", "Programming Language", "Protocol", "None"), "Programming Language")
        addQuestion("Which holds data temporary", listOf("OS", "Programming Language", "Protocol", "RAM"), "RAM")

    }
}