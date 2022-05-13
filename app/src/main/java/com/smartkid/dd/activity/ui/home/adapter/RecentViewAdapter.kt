package com.smartkid.dd.activity.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.smartkid.dd.R
import com.smartkid.dd.activity.ui.home.helper.RecentViewHelper


class RecentViewAdapter constructor(private var recentViewLocations: ArrayList<RecentViewHelper>, private var mOnClickListener: ListItemClickListener) :
    RecyclerView.Adapter<RecentViewAdapter.RecentViewHold?>()  {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentViewHold {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_card_view, parent, false)
        return RecentViewHold(view)
    }

    override fun onBindViewHolder(holder: RecentViewHold, position: Int) {
        val recentViewHelper: RecentViewHelper = recentViewLocations!![position]
        recentViewHelper.getImage()?.let { holder.image.setImageResource(it) }
        holder.title.setText(recentViewHelper.getTitle())
        holder.relativeLayout.background = recentViewHelper.getColor()
    }

    override fun getItemCount(): Int {
        return recentViewLocations!!.size
    }

    interface ListItemClickListener {
        fun onRecentViewListClick(clickedItemIndex: Int)
    }

    class RecentViewHold(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var image: ImageView
        var title: TextView
        var relativeLayout: RelativeLayout
        override fun onClick(v: View?) {
            val clickedPosition = adapterPosition
            val mOnClickListener: ListItemClickListener? = null
            mOnClickListener?.onRecentViewListClick(clickedPosition)
        }

        init {
            itemView.setOnClickListener(this)
            //hooks
            image = itemView.findViewById(R.id.card_image)
            title = itemView.findViewById(R.id.card_title)
            relativeLayout = itemView.findViewById(R.id.recentView_card_container)
        }
    }
}
