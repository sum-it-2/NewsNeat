package com.example.newsneat.MyViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsneat.Repo.Repo
import com.example.newsneat.models.NewsModels
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.http.Query

class MyViewModel: ViewModel() {

    private val _isLoading = MutableStateFlow(true) // Initially true
    val isLoading: StateFlow<Boolean> = _isLoading
    val response = mutableStateOf<NewsModels?>(null)
    private val _hasFetchedOnce = mutableStateOf(false)

    val repo = Repo()

    init {
        fetchNews(query = "india")
    }

    fun fetchNews(query: String){
        viewModelScope.launch(Dispatchers.IO) {
            if (!_hasFetchedOnce.value) {
                _isLoading.value = true
            }
            try {
                val data = repo.newProvider(query)
                response.value = data.body()
                _hasFetchedOnce.value = true
            } catch (e: Exception) {
                response.value = null
            } finally {
                _isLoading.value = false
            }

        }
    }
}