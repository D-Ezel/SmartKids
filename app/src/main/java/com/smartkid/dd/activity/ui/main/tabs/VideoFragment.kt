package com.smartkid.dd.activity.ui.main.tabs

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smartkid.dd.R
import com.smartkid.dd.activity.WatchActivity
import com.smartkid.dd.activity.ui.main.tabs.adapter.VideoAdapter
import com.smartkid.dd.activity.ui.main.tabs.helper.VideoHelper
import com.smartkid.dd.databinding.FragmentVideoBinding

const val IDENTIFICATION = "id"

class VideoFragment : Fragment(), VideoAdapter.ListItemClickListener {
    var videoRecycler: RecyclerView? = null
    var videoAdapter: RecyclerView.Adapter<*>? = null

    private var _binding: FragmentVideoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentVideoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val videoRecyclerCaller: RecyclerView = binding.videoRecycler
        videoRecycler = videoRecyclerCaller.findViewById(R.id.video_recycler)
        videoViewRecycler()

        return root
    }

    private fun videoViewRecycler() {
        videoRecycler?.setHasFixedSize(true)
        videoRecycler?.setLayoutManager(
            LinearLayoutManager(
                activity,
                LinearLayoutManager.VERTICAL,
                false
            )
        )
        val videoList: ArrayList<VideoHelper> = ArrayList()
        videoList.add(VideoHelper(R.drawable.pets, "Les differents animaux domestiques","Daniel", "https"))
        videoList.add(VideoHelper(R.drawable.animaux_sauvage, "Les animaux sauvages", "Yvan", "https"))
        videoList.add(VideoHelper(R.drawable.sound_animal, "Les differentes sons d'animaux", "Sam", "https"))
        videoAdapter = VideoAdapter(videoList, this)
        videoRecycler?.setAdapter(videoAdapter)
    }

    override fun onVideoListClick(title: String?) {
        val intent = Intent(this.context, WatchActivity::class.java).apply {
            putExtra(IDENTIFICATION, title)
        }
        startActivity(intent)
    }

}