package com.smartkid.dd.activity.ui.category.helper

class CategoryHelper {
    private var _id: String?
    private var img: Int
    private var title: String?

    fun get_id(): String? {
        return this._id
    }

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

    constructor(_id:String?, img: Int, title: String?) {
        this._id = _id
        this.img = img
        this.title = title
    }
}