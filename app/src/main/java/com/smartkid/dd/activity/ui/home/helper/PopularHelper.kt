package com.smartkid.dd.activity.ui.home.helper

class PopularHelper {
    private var img: Int
    private var title: String?
    private var rating: Float

    fun getImg(): Int {
        return this.img;
    }

    fun setImg(value: Int) {
        this.img = value
    }

    fun getTitle(): String? {
        return this.title;
    }

    fun setTitle(value: String?) {
        this.title = value
    }

    fun getRating(): Float {
        return this.rating;
    }

    fun setRating(value: Float) {
        this.rating = value
    }

    constructor(img: Int, title: String?, rating: Float) {
        this.img = img
        this.title = title
        this.rating = rating
    }
}