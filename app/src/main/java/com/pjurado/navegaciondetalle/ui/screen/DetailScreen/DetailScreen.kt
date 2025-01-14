package com.pjurado.navegaciondetalle.ui.screen.DetailScreen

import android.icu.text.CaseMap.Title
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.proyectoui.model.MediaItem
import com.pjurado.navegaciondetalle.App
import com.pjurado.navegaciondetalle.data.db.toMediaItem
import com.pjurado.navegaciondetalle.data.repositories.repositoryList
import com.pjurado.navegaciondetalle.ui.screen.ListaScreen.Imagen
import com.pjurado.navegaciondetalle.ui.screen.ListaScreen.Title
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun DetailScreen(id: Int) {
    val movieDao = (LocalContext.current.applicationContext as App).db.movieDao()
    val scope = rememberCoroutineScope()

    var item by remember { mutableStateOf<MediaItem?>(null) }

    LaunchedEffect(null) {
        scope.launch(Dispatchers.IO) {
            //val item = repositoryList.getMedia().find { it.id == id }.let {}
            movieDao.findById(id)?.let {
                item = it.toMediaItem()
            }
        }
    }

    if (item != null) {
        Column {
            Imagen(item!!, modifier = Modifier.height(400.dp))
            Title(item!!)
            item?.overview?.let { Text(it, modifier = Modifier.padding(8.dp)) }
        }
    }
}