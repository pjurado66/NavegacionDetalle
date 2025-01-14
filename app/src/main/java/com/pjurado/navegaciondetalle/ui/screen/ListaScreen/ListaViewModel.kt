package com.pjurado.navegaciondetalle.ui.screen.ListaScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.proyectoui.model.MediaItem
import com.pjurado.navegaciondetalle.data.db.MovieDAO
import com.pjurado.navegaciondetalle.data.db.toMediaItem
import com.pjurado.navegaciondetalle.data.repositories.RemoteConectecition
import com.pjurado.navegaciondetalle.data.repositories.model.toMediaItem
import com.pjurado.navegaciondetalle.data.repositories.model.toMovieDB
import com.pjurado.navegaciondetalle.data.repositories.repositoryList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

data class UiState(
    val loading: Boolean = false,
    val movies: List<MediaItem>? = null,
    val navigateTo: Int? = null
)

class ListaViewModel(val movieDAO: MovieDAO) : ViewModel() {
    private val _uiState: MutableLiveData<UiState> = MutableLiveData(UiState())
    val lista: LiveData<UiState> = _uiState

    init {
        _uiState.value = _uiState.value?.copy(loading = true)
        requestMovies()
        _uiState.value = _uiState.value?.copy(loading = false)
    }

    private fun requestMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            if (movieDAO.movieCount() == 0) {
                val movies =
                    RemoteConectecition.service.getMovies("90ed410279841454998676e620cc1cbb").results
                movieDAO.insertMovies(movies.map { it.toMovieDB() })
            }
            val lista = movieDAO.getAll().map { it.toMediaItem() }
            withContext(Dispatchers.Main) {
                _uiState.value = _uiState.value?.copy(movies = lista)
            }
        }
    }

    fun navigateToDetail(mediaItemId: Int) {
        _uiState.value = _uiState.value?.copy(navigateTo = mediaItemId)
    }

    fun navigateToDetailDone() {
        _uiState.value = _uiState.value?.copy(navigateTo = null)
    }

}

    class ListaViewModelFactory(private val movieDao: MovieDAO) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ListaViewModel(movieDao) as T
        }
    }