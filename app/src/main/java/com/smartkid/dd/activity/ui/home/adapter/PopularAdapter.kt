package com.smartkid.dd.activity.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.smartkid.dd.R
import com.smartkid.dd.activity.ui.home.helper.PopularHelper
import com.smartkid.dd.activity.ui.home.helper.RecentViewHelper

class PopularAdapter constructor(private var popularCardList: ArrayList<PopularHelper>, private var mOnClickListener: ListItemClickListener) :
    RecyclerView.Adapter<PopularAdapter.PopularHold?>()  {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularHold {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_card_popular, parent, false)
        return PopularHold(view)
    }

    override fun onBindViewHolder(holder: PopularHold, position: Int) {
        val popularCardHelper: PopularHelper = popularCardList!![position]
        popularCardHelper.getImg()?.let { holder.img.setImageResource(it) }
        holder.title.setText(popularCardHelper.getTitle())
        holder.rating.rating = popularCardHelper.getRating()
    }

    override fun getItemCount(): Int {
        return popularCardList!!.size
    }

    interface ListItemClickListener {
        fun onPopularCardListClick(clickedItemIndex: Int)
    }

    class PopularHold(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var img: ImageView
        var title: TextView
        var rating: RatingBar
        override fun onClick(v: View?) {
            val clickedPosition = adapterPosition
            val mOnClickListener: ListItemClickListener? = null
            mOnClickListener?.onPopularCardListClick(clickedPosition)
        }

        init {
            itemView.setOnClickListener(this)
            //hooks
            img = itemView.findViewById(R.id.card_img)
            title = itemView.findViewById(R.id.card_title)
            rating = itemView.findViewById(R.id.card_rating)
        }
    }
}