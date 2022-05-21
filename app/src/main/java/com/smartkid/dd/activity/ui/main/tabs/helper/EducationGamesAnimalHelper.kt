package com.smartkid.dd.activity.ui.main.tabs.helper

import android.media.SoundPool
import com.smartkid.dd.activity.ui.main.tabs.models.Animal

class EducationGamesAnimalHelper {
    private var _id: String?
    private var animalObj: ArrayList<Animal>
    private var soundQuiz: SoundPool?
    private var response: String?

    fun get_id(): String? {
        return this._id
    }

    fun set_id(value: String?) {
        this._id = value
    }

    fun getAnimalObj(): ArrayList<Animal> {
        return this.animalObj
    }

    fun setAnimalObj(value: ArrayList<Animal>) {
        this.animalObj = value
    }

    fun getSoundQuiz(): SoundPool? {
        return this.soundQuiz
    }

    fun setSoundQuiz(value: SoundPool?) {
        this.soundQuiz = value
    }

    fun getResponse() : String? {
        return this.response
    }

    fun setResponse(value: String?) {
        this.response = value
    }

    constructor(_id: String?, animalObj: ArrayList<Animal>, soundQuiz: SoundPool?, response: String?) {
        this._id = _id
        this.animalObj = animalObj
        this.soundQuiz = soundQuiz
        this.response = response
    }
}