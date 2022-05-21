package com.smartkid.dd.activity.ui.main.tabs

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smartkid.dd.R
import com.smartkid.dd.activity.WatchActivity
import com.smartkid.dd.activity.ui.main.tabs.adapter.VideoAdapter
import com.smartkid.dd.activity.ui.main.tabs.helper.VideoHelper
import com.smartkid.dd.activity.ui.main.tabs.models.Video
import com.smartkid.dd.api.ApiClient
import com.smartkid.dd.databinding.FragmentVideoBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

const val SOURCE = "src"

class VideoFragment(override val coroutineContext: CoroutineContext) : Fragment(), VideoAdapter.ListItemClickListener, CoroutineScope {
    var videoRecycler: RecyclerView? = null
    var videoAdapter: RecyclerView.Adapter<*>? = null
    var videoList : MutableList<Video> = ArrayList()
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
        videoList()

        /*val videoListHelper: ArrayList<VideoHelper> = ArrayList()
        videoListHelper.add(VideoHelper(/*R.drawable.pets*/0, "Les differents animaux domestiques","Daniel", "https"))
        videoListHelper.add(VideoHelper(/*R.drawable.animaux_sauvage*/0, "Les animaux sauvages", "Yvan", "https"))
        videoListHelper.add(VideoHelper(/*R.drawable.sound_animal*/0, "Les differentes sons d'animaux", "Sam", "https"))
        videoAdapter = VideoAdapter(videoListHelper, this)
        videoRecycler?.setAdapter(videoAdapter)*/
    }

    override fun onVideoListClick(title: String?) {
        val intent = Intent(this.context, WatchActivity::class.java).apply {
            putExtra(SOURCE, title)
        }
        startActivity(intent)
    }

    private fun videoList() {
        val idCategory = this.activity?.intent?.getStringExtra(com.smartkid.dd.activity.ui.category.IDENTIFICATION)
        launch(Dispatchers.Main) {
            val response :  Response<MutableList<Video>>
            try {
                response = ApiClient.apiService.getVideo(idCategory)

                if (response.isSuccessful && response.body() != null) {
                    val content = response.body()
                    if (content != null) {
                        videoList = content
                        val videoListHelper: ArrayList<VideoHelper> = ArrayList()
                        for(videoItem in videoList) {
                            videoListHelper.add(VideoHelper(videoItem.thumbnail, videoItem.titre, videoItem.auteur, videoItem.video, videoItem.duration))
                        }
                        videoAdapter = VideoAdapter(videoListHelper, this@VideoFragment)
                        videoRecycler?.setAdapter(videoAdapter)
                    }
//do something
                } else {
                    Toast.makeText(
                        this@VideoFragment.context,
                        "Error Occurred: ${response.message()}",
                        Toast.LENGTH_LONG
                    ).show()
                }

            } catch (e: Exception) {
                Toast.makeText(
                    this@VideoFragment.context,
                    "Error Occurred: ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

}