package com.smartkid.dd.activity.ui.settings
import java.util.*

internal object  SettingsData {
    val data: HashMap<String, List<String>>
    get() {
        val expandableListDetail =
            HashMap<String, List<String>>()

        val preferences: MutableList<String> =
            ArrayList()
        preferences.add("Affichage notifications")
        preferences.add("Vibration notifications")

        val compte: MutableList<String> = ArrayList()
        compte.add("Log out")

        expandableListDetail["Préférences"] = preferences
        expandableListDetail["Compte"] = compte

        return expandableListDetail
    }
}