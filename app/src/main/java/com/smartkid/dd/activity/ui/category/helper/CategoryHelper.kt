package com.smartkid.dd.activity.ui.category.helper

class CategoryHelper {
    private var img: Int
    private var title: String?

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

    constructor(img: Int, title: String?) {
        this.img = img
        this.title = title
    }
}