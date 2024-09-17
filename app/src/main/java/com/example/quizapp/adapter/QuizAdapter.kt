package com.example.quizapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.QuizActivity
import com.example.quizapp.databinding.QuizItemRowBinding
import com.example.quizapp.model.QuizModel
import com.example.quizapp.utility.DataProvider

class QuizAdapter(private val quizList: List<QuizModel>) : RecyclerView.Adapter<QuizAdapter.QuizVH>() {

        class QuizVH(private val binding: QuizItemRowBinding):RecyclerView.ViewHolder(binding.root){

            fun bind(quizModel: QuizModel){
                binding.apply {
                    itemQuizTitle.text = quizModel.title
                    itemSubtitle.text = quizModel.subtitle
                    itemTime.text =  "${quizModel.time} min"
                    root.setOnClickListener {
                        val intent = Intent(root.context, QuizActivity::class.java)
//                        val message = "${quizModel.title}\n${quizModel.subtitle}"
//                        intent.putExtra("KEY", message)
                        QuizActivity.quizModelList = quizModel.questionsList
                        QuizActivity.time = "${quizModel.time}"
                        root.context.startActivity(intent)
                    }
                }

            }

            companion object {
                fun inflateFrom(parent: ViewGroup) : QuizVH {
                    val inflater = LayoutInflater.from(parent.context)
                    val binding = QuizItemRowBinding.inflate(inflater,parent,false)
                    return  QuizVH(binding)
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizVH = QuizVH.inflateFrom(parent)

    override fun getItemCount(): Int = quizList.size

    override fun onBindViewHolder(holder: QuizVH, position: Int) {
        val currentQuiz = quizList[position]
        holder.bind(currentQuiz)
    }
}