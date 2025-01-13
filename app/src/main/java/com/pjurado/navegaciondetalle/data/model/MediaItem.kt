package com.example.proyectoui.model


data class MediaItem(
    val id: Int,
    val title: String,
    val photo: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val voteAverage: Double
)
