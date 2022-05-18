package com.smartkid.dd.activity.ui.main.tabs.helper

class VideoHelper {
    private var img: Int
    private var title: String?
    private var author: String?
    private var src: String?

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

    fun getAuthor(): String? {
        return this.author;
    }

    fun setAuthor(value: String?) {
        this.author = value
    }

    fun getSrc(): String? {
        return this.src;
    }

    fun setSrc(value: String?) {
        this.src = value
    }

    constructor(img: Int, title: String?, author: String?, src: String?) {
        this.img = img
        this.title = title
        this.author = author
        this.src = src
    }
}