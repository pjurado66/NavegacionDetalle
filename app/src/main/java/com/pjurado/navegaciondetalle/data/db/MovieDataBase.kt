package com.pjurado.navegaciondetalle.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MovieDB::class], version = 1)
abstract class MovieDataBase: RoomDatabase() {
    abstract fun movieDao(): MovieDAO
}