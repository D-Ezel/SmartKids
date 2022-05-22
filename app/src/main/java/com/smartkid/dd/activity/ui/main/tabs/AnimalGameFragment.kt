package com.smartkid.dd.activity.ui.main.tabs

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.smartkid.dd.R
import com.smartkid.dd.activity.ui.main.tabs.models.Question
import com.smartkid.dd.api.ApiClient
import com.smartkid.dd.databinding.FragmentAnimalGameBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import kotlin.coroutines.CoroutineContext

class AnimalGameFragment(override val coroutineContext: CoroutineContext, val parent:Fragment) : Fragment(), CoroutineScope {

    private lateinit var audio: ImageButton
    private lateinit var option1: Button
    private lateinit var option2: Button
    private lateinit var option3: Button
    private lateinit var option4: Button
    private var soundUrl: String? = null
    private var reponse: String? = null
    private var position: Int = 0

    private var questionList : MutableList<Question> = ArrayList()

    private var _binding: FragmentAnimalGameBinding? = null
    var mediaPlayer: MediaPlayer? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAnimalGameBinding.inflate(inflater, container, false)
        val root: View = binding.root

        audio = root.findViewById(R.id.audio_btn)
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

        audio.setOnClickListener {
            playAudio()
        }

        questionList()

        return root
    }

    private fun checkAnswer(userAnswer: String?) {
        if(reponse?.lowercase().equals(userAnswer?.lowercase())) {
            position++
            next()
            stopAudio()
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

        soundUrl = questionList.get(position).audio
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
                        soundUrl = questionList.get(position).audio
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
        stopAudio()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopAudio()
    }

    private fun playAudio() {
        val audioURL = soundUrl
        mediaPlayer = MediaPlayer()
        mediaPlayer!!.setAudioStreamType(AudioManager.STREAM_MUSIC)
        try {
            mediaPlayer!!.setDataSource(audioURL)
            mediaPlayer!!.prepare()
            mediaPlayer!!.start()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        Toast.makeText(parent.context, "Audio en marche", Toast.LENGTH_SHORT).show()
    }

    private fun stopAudio() {
        if(mediaPlayer!!.isPlaying) {
            mediaPlayer!!.stop()
            mediaPlayer!!.reset()
            mediaPlayer!!.release()
            mediaPlayer = null
        }
    }
}