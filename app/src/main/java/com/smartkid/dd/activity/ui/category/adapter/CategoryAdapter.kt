package com.smartkid.dd.activity.ui.category.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.smartkid.dd.R
import com.smartkid.dd.activity.ui.category.helper.CategoryHelper

class CategoryAdapter constructor(private var categoryHeplerList: ArrayList<CategoryHelper>, private var mOnClickListener: CategoryAdapter.ListItemClickListener) :
    RecyclerView.Adapter<CategoryAdapter.CategoryHold?>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHold {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_card_category, parent, false)
        return CategoryHold(view)
    }

    override fun onBindViewHolder(holder: CategoryHold, position: Int) {
        val categoryHepler: CategoryHelper = categoryHeplerList!![position]
        categoryHepler.getImg()?.let { holder.img.setImageResource(it) }
        holder.title.setText(categoryHepler.getTitle())
    }

    override fun getItemCount(): Int {
        return categoryHeplerList!!.size
    }

    interface ListItemClickListener {
        fun onCategoryListClick(clickedItemIndex: Int)
    }

    class CategoryHold(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var img: ImageView
        var title: TextView
        override fun onClick(v: View?) {
            val clickedPosition = adapterPosition
            val mOnClickListener: ListItemClickListener? = null
            mOnClickListener?.onCategoryListClick(clickedPosition)
        }

        init {
            itemView.setOnClickListener(this)
            //hooks
            img = itemView.findViewById(R.id.card_img)
            title = itemView.findViewById(R.id.card_title)
        }
    }
}