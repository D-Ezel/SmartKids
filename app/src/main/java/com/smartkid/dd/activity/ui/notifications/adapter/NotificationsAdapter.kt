package com.smartkid.dd.activity.ui.notifications.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.smartkid.dd.R
import com.smartkid.dd.activity.ui.notifications.helper.NotificationsHelper

class NotificationsAdapter constructor(private var notificationsHeplerList: ArrayList<NotificationsHelper>, private var mOnClickListener: NotificationsAdapter.ListItemClickListener) :
    RecyclerView.Adapter<NotificationsAdapter.NotificationsHold?>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationsHold {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_card_notifications, parent, false)
        return NotificationsHold(view)
    }

    override fun onBindViewHolder(holder: NotificationsHold, position: Int) {
        val notificationsHeplerSingle: NotificationsHelper = notificationsHeplerList!![position]
        notificationsHeplerSingle.getImg()?.let { holder.img.setImageResource(it) }
        holder.title.setText(notificationsHeplerSingle.getTitle())
        holder.timeToString.setText(notificationsHeplerSingle.getTimeToString())
    }

    override fun getItemCount(): Int {
        return notificationsHeplerList!!.size
    }

    interface ListItemClickListener {
        fun onNotificationsListClick(clickedItemIndex: Int)
    }

    class NotificationsHold(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var img: ImageView
        var title: TextView
        var timeToString: TextView
        override fun onClick(v: View?) {
            val clickedPosition = adapterPosition
            val mOnClickListener: ListItemClickListener? = null
            mOnClickListener?.onNotificationsListClick(clickedPosition)
        }

        init {
            itemView.setOnClickListener(this)
            //hooks
            img = itemView.findViewById(R.id.img_notif_card)
            title = itemView.findViewById(R.id.title_notif_card)
            timeToString = itemView.findViewById(R.id.time_notif_card)
        }
    }
}