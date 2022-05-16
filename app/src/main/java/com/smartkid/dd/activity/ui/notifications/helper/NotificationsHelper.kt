package com.smartkid.dd.activity.ui.notifications.helper

import java.sql.Time

class NotificationsHelper {
    private var img: Int
    private var title: String?
    private var timeToString: String?
    private var time: Time?

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

    fun getTimeToString(): String? {
        return this.timeToString;
    }

    fun setTimeToString(value: String?) {
        this.timeToString = value
    }

    fun getTime(): Time? {
        return this.time;
    }

    fun setTime(value: Time?) {
        this.time = value
    }

    constructor(img: Int, title: String?, timeToString: String?, time:Time) {
        this.img = img
        this.title = title
        this.timeToString = timeToString
        this.time = time
    }
}