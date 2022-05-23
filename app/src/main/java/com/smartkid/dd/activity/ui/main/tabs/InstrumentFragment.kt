package com.smartkid.dd.activity.ui.main.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.smartkid.dd.R
import com.smartkid.dd.activity.ui.main.tabs.models.Question
import com.smartkid.dd.api.ApiClient

import com.smartkid.dd.databinding.FragmentInstrumentBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class InstrumentFragment(override val coroutineContext: CoroutineContext, val parent:Fragment) : Fragment(),
    CoroutineScope {

    private lateinit var image: ImageView
    private lateinit var option1: Button
    private lateinit var option2: Button
    private lateinit var option3: Button
    private lateinit var option4: Button
    private var reponse: String? = null
    private var position: Int = 0

    private var questionList : MutableList<Question> = ArrayList()

    private var _binding: FragmentInstrumentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInstrumentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        image = root.findViewById(R.id.image_instru)
        option1 = root.findViewById(R.id.option1)
        option2 = root.findViewById(R.id.option2)
        option3 = root.findViewById(R.id.option3)
        option4 = root.findViewById(R.id.option4)

        option1.setOnClickListener {
            checkAnswer(option1.text.toString())
        }

        option2.setOnClickListener {
            checkAnswer(option2.text.toString())
        }

        option3.setOnClickListener {
            checkAnswer(option3.text.toString())
        }

        option4.setOnClickListener {
            checkAnswer(option4.text.toString())
        }

        questionList()

        return root
    }

    private fun checkAnswer(userAnswer: String?) {
        if(reponse?.lowercase().equals(userAnswer?.lowercase())) {
            position++
            next()
            Toast.makeText(
                parent.context,
                "Correcte",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            Toast.makeText(
                parent.context,
                "Incorrecte Essaie encore",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun next() {
        val animImage = AnimationUtils.loadAnimation(parent.context, R.anim.lefttoright)
        animImage.startOffset = 100
        image.startAnimation(animImage)

        val animOption1 = AnimationUtils.loadAnimation(parent.context, R.anim.lefttoright)
        animOption1.startOffset = 100
        option1.startAnimation(animOption1)

        val animOption2 = AnimationUtils.loadAnimation(parent.context, R.anim.lefttoright)
        animOption2.startOffset = 100
        option2.startAnimation(animOption2)

        val animOption3 = AnimationUtils.loadAnimation(parent.context, R.anim.lefttoright)
        animOption3.startOffset = 100
        option3.startAnimation(animOption3)

        val animOption4 = AnimationUtils.loadAnimation(parent.context, R.anim.lefttoright)
        animOption4.startOffset = 100
        option4.startAnimation(animOption4)

        Glide.with(image.context).load(questionList.get(position).image).into(image)
        option1.text = questionList.get(position).options.get(0)
        option2.text = questionList.get(position).options.get(1)
        option3.text = questionList.get(position).options.get(2)
        option4.text = questionList.get(position).options.get(3)
        reponse = questionList.get(position).reponse

        if(position == questionList.size - 1) {
            position = 0
        }
    }


    private fun questionList() {
        val idCategory = parent.activity?.intent?.getStringExtra(com.smartkid.dd.activity.ui.category.IDENTIFICATION)
        launch(Dispatchers.Main) {
            val response : Response<MutableList<Question>>
            try {
                response = ApiClient.apiService.getQuestions("627a5da5bbf7dd32f8411326",idCategory)

                if (response.isSuccessful && response.body() != null) {
                    val content = response.body()
                    if (content != null) {
                        questionList = content
                        Glide.with(image.context).load(questionList.get(position).image).into(image)
                        option1.text = questionList.get(position).options.get(0)
                        option2.text = questionList.get(position).options.get(1)
                        option3.text = questionList.get(position).options.get(2)
                        option4.text = questionList.get(position).options.get(3)
                        reponse = questionList.get(position).reponse
                    }
//do something
                } else {
                    Toast.makeText(
                        parent.context,
                        "Error Occurred: ${response.message()}",
                        Toast.LENGTH_LONG
                    ).show()
                }

            } catch (e: Exception) {
                Toast.makeText(
                    parent.context,
                    "Error Occurred: ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}