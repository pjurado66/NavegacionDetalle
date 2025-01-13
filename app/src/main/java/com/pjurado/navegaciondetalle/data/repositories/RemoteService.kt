package com.pjurado.navegaciondetalle.data.repositories

import com.pjurado.navegaciondetalle.data.repositories.model.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteService {
    @GET("discover/movie?language=es ES&sort_by=popularity.desc")
    suspend fun getMovies(@Query("api_key")apiKey: String): Response
}