package com.example.quizapp

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapp.adapter.QuizAdapter
import com.example.quizapp.databinding.ActivityMainBinding
import com.example.quizapp.model.QuizModel
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var quizAdapter: QuizAdapter
    private lateinit var quizModelList: MutableList<QuizModel>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        quizModelList = mutableListOf()

        getQuizFromJsonOrFirebase()

    }

    private fun getQuizFromJsonOrFirebase(){
        binding.dataFetchProgressBar.visibility = View.VISIBLE
        FirebaseDatabase.getInstance().reference
            .get()
            .addOnSuccessListener { dataSnapShot ->
                if (dataSnapShot.exists()){
                    for (snapshot in dataSnapShot.children){
                         val quizModel = snapshot.getValue(QuizModel::class.java)
                        if (quizModel != null) {
                            Log.d("Quiz", "${quizModel.questionsList}")
                            quizModelList.add(quizModel)
                        }
                    }
//                    quizModelList = DataProvider.quizList
                    binding.dataFetchProgressBar.visibility = View.GONE
                    quizAdapter = QuizAdapter(quizModelList)
                    binding.recyclerView.adapter = quizAdapter
                }
            }
//        val jsonFile =loadFromJson(this,"DemoJSONQuiz.json")
//        val quizList = jsonFile?.let { parseQuizData(it) }
//        val quizList = DataProvider.quizList

//        quizAdapter = quizList?.let { QuizAdapter(it) }!!

    }

}