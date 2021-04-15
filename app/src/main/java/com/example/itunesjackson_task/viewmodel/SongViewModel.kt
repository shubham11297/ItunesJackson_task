package com.example.itunesjackson_task.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.itunesjackson_task.network.data.SongData
import com.example.itunesjackson_task.repo.SongRepository
import com.example.itunesjackson_task.utils.Coroutines

class SongViewModel: ViewModel() {

    private val repository = SongRepository()
    var dataRetrieved = MutableLiveData<Boolean>().apply { postValue(false) }
    var fullData : SongData? = null

    var selectedPosition = 0

    fun getSongData(){
        Coroutines.io {
            val response = repository.getSongData()
            if (response.isSuccessful) {
                fullData = response.body()
                dataRetrieved.postValue(true)
            }
        }
    }

    fun returnSelectedData() = fullData!!.results[selectedPosition]

}

class SongViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SongViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return SongViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}