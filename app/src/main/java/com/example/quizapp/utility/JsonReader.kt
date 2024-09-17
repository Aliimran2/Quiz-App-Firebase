package com.example.quizapp.utility

import android.content.Context
import com.example.quizapp.model.QuizModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

fun loadFromJson(context: Context, fileName: String): String? {
    val jsonString:String
    try {
        jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
    }catch (io:IOException){
        io.printStackTrace()
        return null
    }
    return jsonString
}

fun parseQuizData(jsonString: String):List<QuizModel>? {
    val gson = Gson()
    val listQuizType = object: TypeToken<List<QuizModel>>(){}.type
    return gson.fromJson(jsonString,listQuizType)
}