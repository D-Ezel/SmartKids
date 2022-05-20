package com.smartkid.dd.activity.ui.settings
import java.util.*

internal object  SettingsData {
    val data: HashMap<String, List<String>>
    get() {
        val expandableListDetail =
            HashMap<String, List<String>>()

        val preferences: MutableList<String> =
            ArrayList()
        preferences.add("Notifications' display")
        preferences.add("Notifications' vibrations")

        val compte: MutableList<String> = ArrayList()
        compte.add("Log out")

        expandableListDetail["Préférences"] = preferences
        expandableListDetail["Compte"] = compte

        return expandableListDetail
    }
}