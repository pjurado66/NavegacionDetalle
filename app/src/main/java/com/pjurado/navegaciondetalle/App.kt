package com.pjurado.navegaciondetalle

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pjurado.navegaciondetalle.data.db.MovieDataBase


class App: Application() {
    lateinit var db: MovieDataBase

    override fun onCreate() {
        super.onCreate()
        db = Room
            .databaseBuilder(applicationContext, MovieDataBase::class.java, "movie-db")
            .build()
    }
}