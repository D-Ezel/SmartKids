package com.smartkid.dd.activity.ui.main.tabs.helper

class VideoHelper {
    private var img: String?
    private var title: String?
    private var author: String?
    private var src: String?
    private var duration: String?

    fun getDuration(): String? {
        return this.duration;
    }

    fun setDuration(value: String?) {
        this.duration = value
    }

    fun getImg(): String? {
        return this.img;
    }

    fun setImg(value: String?) {
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

    constructor(img: String?, title: String?, author: String?, src: String?, duration: String?) {
        this.img = img
        this.title = title
        this.author = author
        this.src = src
        this.duration = duration
    }
}