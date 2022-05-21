package com.smartkid.dd.activity.ui.main.tabs.models

data class Video(
    val _id: String? = null,
    val titre: String? = null,
    val duration: String? = null,
    val thumbnail: String? = null,
    val video: String? = null,
    val auteur:  String? = null,
    val idCategorie: Categorie = Categorie()
)
