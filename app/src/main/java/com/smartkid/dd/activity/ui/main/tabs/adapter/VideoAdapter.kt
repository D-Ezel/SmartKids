package com.smartkid.dd.activity.ui.main.tabs.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.smartkid.dd.R
import com.smartkid.dd.activity.ui.main.tabs.helper.VideoHelper

class VideoAdapter constructor(private var videoList: ArrayList<VideoHelper>, private var mOnClickListener: ListItemClickListener) :
    RecyclerView.Adapter<VideoAdapter.VideoHold?>()  {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoHold {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_video, parent, false)
        return VideoHold(view)
    }

    override fun onBindViewHolder(holder: VideoHold, position: Int) {
        val videoHelper: VideoHelper = videoList!![position]
        videoHelper.getImg()?.let { holder.img.setImageResource(it) }
        holder.title.setText(videoHelper.getTitle())
        holder.author.text = videoHelper.getAuthor()
        holder.playVideo.setOnClickListener {
            mOnClickListener.onVideoListClick(videoHelper.getTitle())
        }
    }

    override fun getItemCount(): Int {
        return videoList!!.size
    }

    interface ListItemClickListener {
        fun onVideoListClick(title: String?)
    }

    class VideoHold(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var img: ImageView
        var title: TextView
        var author: TextView
        var playVideo: ImageButton
        override fun onClick(v: View?) {
        }

        init {
            itemView.setOnClickListener(this)
            //hooks
            img = itemView.findViewById(R.id.img_educ_games)
            title = itemView.findViewById(R.id.title_video)
            author = itemView.findViewById(R.id.author_video)
            playVideo = itemView.findViewById(R.id.play_video)
        }
    }
}