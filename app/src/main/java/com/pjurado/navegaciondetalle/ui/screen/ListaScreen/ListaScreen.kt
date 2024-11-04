package com.pjurado.navegaciondetalle.ui.screen.ListaScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.filled.PlayCircleOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.proyectoui.model.MediaItem
import com.example.proyectoui.model.Type
import com.pjurado.navegaciondetalle.R
import com.pjurado.navegaciondetalle.data.repositories.repositoryList

@Composable
fun ListaScreen(navigateToDetail: (Int) -> Unit) {
    val lista = repositoryList.getMedia()
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        //modifier = Modifier.fillMaxSize()
    ) {
        items(lista) { mediaItem ->
            MediaListItem(mediaItem, navigateToDetail)
        }
    }
}

@Composable
private fun MediaListItem(mediaItem: MediaItem, navigateToDetail: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .width(200.dp)
            .padding(2.dp)
            .clickable { navigateToDetail(mediaItem.id) },
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Imagen(item = mediaItem)
        Title(item = mediaItem)
    }
}

@Composable
fun Imagen(item: MediaItem, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {

        Image(
            painter = rememberAsyncImagePainter(
                model = item.photo,
                imageLoader = ImageLoader.Builder(context).crossfade(true).build()
            ),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        if (item.tipo == Type.VIDEO) {
            Icon(
                imageVector = Icons.Default.PlayCircleOutline,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(92.dp)
                    .align(Alignment.Center)
            )
        }

    }
}

@Composable
fun Title(item: MediaItem) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.secondary)
            .padding(16.dp)
    ) {
        Text(
            text = item.title,
            style = MaterialTheme.typography.displaySmall
        )
    }
}


