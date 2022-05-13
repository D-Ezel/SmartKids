package com.smartkid.dd.activity.ui.home.helper

class PopularHelper {
    private var img: Int
    private var title: String?
    private var description: String?

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

    fun getDescription(): String? {
        return this.description;
    }

    fun setDescription(value: String?) {
        this.description = value
    }

    constructor(img: Int, title: String?, description: String?) {
        this.img = img
        this.title = title
        this.description = description
    }
}