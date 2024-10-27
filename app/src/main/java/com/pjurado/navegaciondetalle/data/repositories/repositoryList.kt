package com.pjurado.navegaciondetalle.data.repositories

import com.example.proyectoui.model.MediaItem
import com.example.proyectoui.model.Type

object repositoryList {
    fun getMedia() = (1..100).map {
        MediaItem(
            id = it,
            title = "Titulo $it",
            photo = "https://loremflickr.com/400/400/seville?lock=$it",
            tipo = if (it % 3 == 0) Type.VIDEO else Type.FOTO
        )
    }
}