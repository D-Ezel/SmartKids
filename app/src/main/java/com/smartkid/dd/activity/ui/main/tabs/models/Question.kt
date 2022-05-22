package com.smartkid.dd.activity.ui.main.tabs.models

data class Question(
    val _id: String? = null,
    val options: ArrayList<String?> = ArrayList(),
    val reponse: String? = null,
    val idCategorie: String? = null,
    val audio: String? = null,
    val image: String? = null
)
