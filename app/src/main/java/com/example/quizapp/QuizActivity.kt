package com.example.quizapp

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapp.databinding.ActivityQuizBinding
import com.example.quizapp.databinding.ResultDialogBoxBinding
import com.example.quizapp.model.QuestionModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class QuizActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        var quizModelList: List<QuestionModel> = listOf()
        var time = ""

    }

    var selectedAnswer = ""
    var score = 0
    private var currentQuestionIndex = 0

    private lateinit var binding: ActivityQuizBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.apply {
            optionA.setOnClickListener(this@QuizActivity)
            optionB.setOnClickListener(this@QuizActivity)
            optionC.setOnClickListener(this@QuizActivity)
            optionD.setOnClickListener(this@QuizActivity)
            nextBtn.setOnClickListener(this@QuizActivity)
        }
        binding.timer.text = time

        startTimer()
        loadQuestions()


    }

    private fun startTimer() {
        val totalTimeInMillis = time.toInt() * 60 * 1000L
        object : CountDownTimer(totalTimeInMillis, 1000L) {
            override fun onTick(millisUntilFinished: Long) {
                val sec = millisUntilFinished / 1000
                val min = sec / 60
                val remSec = sec % 60
                binding.timer.text = String.format("%02d:%02d", min, remSec)

            }

            override fun onFinish() {
                //finish logic
                finishQuiz()

            }

        }.start()
    }

    private fun loadQuestions() {

        if (currentQuestionIndex == quizModelList.size) {
            finishQuiz()
            return
        }

        binding.apply {
            currentQuestionIndexTv.text =
                "Question ${currentQuestionIndex + 1}/${quizModelList.size}"
            progressBar.progress =
                (currentQuestionIndex.toFloat() / quizModelList.size.toFloat() * 100).toInt()
            questionTv.text = quizModelList[currentQuestionIndex].question
            optionA.text = quizModelList[currentQuestionIndex].options[0]
            optionB.text = quizModelList[currentQuestionIndex].options[1]
            optionC.text = quizModelList[currentQuestionIndex].options[2]
            optionD.text = quizModelList[currentQuestionIndex].options[3]
        }
    }

    private fun finishQuiz() {
        val totalQuestions = quizModelList.size
        val progress = (score.toFloat() / totalQuestions.toFloat() * 100).toInt()


        val dialogBoxBinding = ResultDialogBoxBinding.inflate(layoutInflater)
        dialogBoxBinding.circularIndicator.progress = progress
        dialogBoxBinding.resultScore.text = "Your score is $score out of $totalQuestions"
        dialogBoxBinding.titleScore.text = if(progress>=40) "Congrates! You passed." else "Sorry! You failed."
        dialogBoxBinding.percent.text = "$progress%"

        MaterialAlertDialogBuilder(this)
            .setView(dialogBoxBinding.root)
            .show()

        dialogBoxBinding.finishBtn.setOnClickListener {
            finish()
        }


    }

    override fun onClick(v: View?) {
        val clickedBtn = v as Button
        binding.apply {
            optionA.setBackgroundColor(getColor(R.color.gray))
            optionB.setBackgroundColor(getColor(R.color.gray))
            optionC.setBackgroundColor(getColor(R.color.gray))
            optionD.setBackgroundColor(getColor(R.color.gray))
        }

        if (clickedBtn.id == R.id.nextBtn) {
            if (selectedAnswer == quizModelList[currentQuestionIndex].correct) {
                score++
                Log.i("SCORE", "$score")
            }
            currentQuestionIndex++
            loadQuestions()
        } else {
            selectedAnswer = clickedBtn.text.toString()
            clickedBtn.setBackgroundColor(getColor(R.color.orange))


        }
    }
}