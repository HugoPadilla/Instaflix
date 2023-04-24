package com.example.instaflix.data.remote.dto


import com.google.gson.annotations.SerializedName

data class TvShowResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val tvShowDtos: List<TvShowDto>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)