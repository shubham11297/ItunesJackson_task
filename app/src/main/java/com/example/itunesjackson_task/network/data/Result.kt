package com.example.itunesjackson_task.network.data

data class Result(
    val wrapperType: String,
    val artistId: Int,
    val collectionId: Int,
    val artistName: String,
    val collectionName: String,
    val collectionCensoredName: String,
    val artistViewUrl: String,
    val collectionViewUrl: String,
    val artworkUrl60: String,
    val artworkUrl100: String,
    val collectionPrice: Double,
    val collectionExplicitness: String,
    val trackCount: Int,
    val copyright: String,
    val country: String,
    val currency: String,
    val releaseDate: String,
    val primaryGenreName: String,
    val previewUrl: String,
    val description: String,
    val kind: String,
    val trackId: Int,
    val trackName: String,
    val trackCensoredName: String,
    val trackViewUrl: String,
    val artworkUrl30: String,
    val trackPrice: Double,
    val trackExplicitness: String,
    val discCount: Int,
    val discNumber: Int,
    val trackNumber: Int,
    val trackTimeMillis: Int,
    val isStreamable: Boolean,
    val collectionArtistId: Int,
    val collectionArtistName: String,
    val collectionArtistViewUrl: String
)