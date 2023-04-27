package com.example.instaflix.data.remote.dto


import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("dates")
    val datesDto: DatesDto? = null,
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("results")
    val movieDtos: List<MovieDto> = listOf(),
    @SerializedName("total_pages")
    val totalPages: Int? = null,
    @SerializedName("total_results")
    val totalResults: Int? = null
)