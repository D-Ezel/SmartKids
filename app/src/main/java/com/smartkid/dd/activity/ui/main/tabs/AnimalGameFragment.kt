package com.smartkid.dd.activity.ui.main.tabs

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import com.smartkid.dd.R
import com.smartkid.dd.databinding.FragmentAnimalGameBinding
import java.io.IOException

class AnimalGameFragment(val parent:Fragment) : Fragment() {

    private lateinit var audio: ImageButton
    private lateinit var option_one: Button
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
        option_one = root.findViewById(R.id.option_one)
        audio.setOnClickListener {
            playAudio()
        }
        option_one.setOnClickListener {
            stopAudio()
        }

        return root
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
        val audioURL = "https://api-smart-kid.herokuapp.com/assets/chien.mp3"
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