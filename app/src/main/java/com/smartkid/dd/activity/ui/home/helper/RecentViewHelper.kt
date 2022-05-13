package com.smartkid.dd.activity.ui.home.helper

import android.graphics.drawable.GradientDrawable

class RecentViewHelper {
    private var color: GradientDrawable?
    private var image: Int
    private var title: String?

    fun getColor(): GradientDrawable? {
        return this.color;
    }

    fun setColor(value: GradientDrawable) {
        this.color = value
    }

    fun getImage(): Int? {
        return this.image;
    }

    fun setImage(value: Int) {
        this.image = value
    }

    fun getTitle(): String? {
        return this.title;
    }

    fun setTitle(value: String?) {
        this.title = value
    }

    constructor(color: GradientDrawable?, image: Int, title: String?) {
        this.color = color
        this.image = image
        this.title = title
    }

}