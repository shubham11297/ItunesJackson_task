package com.example.itunesjackson_task.network

import com.example.itunesjackson_task.network.data.SongData
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://itunes.apple.com/"
private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

val okHttp: OkHttpClient.Builder = OkHttpClient.Builder()
    .addInterceptor(logger)

interface ApiServices {

    @GET("search?term=Michael+jackson")
    suspend fun getData(): Response<SongData>

    companion object {
        private val gson = Gson()
        operator fun invoke(): ApiServices {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttp.build())
                .build()
                .create(ApiServices::class.java)
        }
    }
}