package com.pjurado.navegaciondetalle.data.repositories

import com.example.proyectoui.model.MediaItem
import com.example.proyectoui.model.Type
import kotlinx.coroutines.delay

object repositoryList {
    suspend fun getMedia(nombre: String): List<MediaItem> {
        delay(2000)
        return (1..100).map {
            MediaItem(
                id = it,
                title = "Titulo $it",
                photo = "https://loremflickr.com/400/400/$nombre?lock=$it",
                tipo = if (it % 3 == 0) Type.VIDEO else Type.FOTO
            )
        }
    }
}