package com.pjurado.navegaciondetalle.ui.screen.ListaScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectoui.model.MediaItem
import com.pjurado.navegaciondetalle.data.repositories.RemoteConectecition
import com.pjurado.navegaciondetalle.data.repositories.model.toMediaItem
import com.pjurado.navegaciondetalle.data.repositories.repositoryList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class ListaViewModel: ViewModel() {
    private val _lista: MutableLiveData<List<MediaItem>> = MutableLiveData()
    val lista: LiveData<List<MediaItem>> = _lista

    private val _progressBar: MutableLiveData<Boolean> = MutableLiveData(false)
    val progressBar: LiveData<Boolean> = _progressBar

    init {
        _progressBar.value = true
        viewModelScope.launch() {
            //_lista.value = repositoryList.getMedia("Seville")
            val movies = RemoteConectecition.service.getMovies("90ed410279841454998676e620cc1cbb")
            _lista.value = movies.results.map {
                it.toMediaItem()
            }
            _progressBar.value = false
        }
    }
}