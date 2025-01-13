package com.pjurado.navegaciondetalle.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDAO {

    @Query("SELECT * FROM MovieDB")
    suspend fun getAll(): List<MovieDB>

    @Query("SELECT * FROM MovieDB WHERE id = :id")
    suspend fun findById(id: Int): MovieDB

    @Query("SELECT COUNT(id) FROM MovieDB")
    suspend fun movieCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovies(movies: List<MovieDB>)

}