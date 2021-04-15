package com.example.itunesjackson_task.repo

import com.example.itunesjackson_task.network.ApiServices

class SongRepository {
    private val api = ApiServices()

    suspend fun getSongData()= api.getData()

}