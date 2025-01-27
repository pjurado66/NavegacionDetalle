package com.pjurado.navegaciondetalle.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.proyectoui.model.MediaItem

@Entity
data class MovieDB(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val adult: Boolean,
    val backdrop_path: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val title: String,
    val vote_average: Double,
    val vote_count: Int
)

fun MovieDB.toMediaItem() = MediaItem(
    id,
    title,
    "https://image.tmdb.org/t/p/w185" + poster_path,
    original_language,
    original_title,
    overview,
    vote_average
)
