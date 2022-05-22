package com.smartkid.dd.activity.ui.main.tabs.helper

class AnimalGameHelper {
    private var nameOptions: ArrayList<String?>
    private var nameTruth: String?
    private var soundUrl: String?

    fun getNameOptions(): ArrayList<String?> {
        return this.nameOptions
    }

    fun setNameOptions(value: ArrayList<String?>) {
        this.nameOptions = value
    }

    fun getNameTruth(): String? {
        return this.nameTruth;
    }

    fun setNameTruth(value: String?) {
        this.nameTruth = value
    }

    fun getSoundUrl(): String? {
        return this.soundUrl
    }

    fun setSoundUrl(value: String?) {
        this.soundUrl = value
    }

    constructor(nameOptions:ArrayList<String?>, nameTruth: String?, soundUrl: String?) {
        this.nameOptions = nameOptions
        this.nameTruth = nameTruth
        this.soundUrl = soundUrl
    }
}