package com.example.newsneat.screens.nav

import kotlinx.serialization.Serializable

sealed class Routes {

    @Serializable
    object MainScreenRoutes

    @Serializable
    data class DetailScreenRoutes(
        val content: String,
        val description: String,
        val publishedAt: String,
        val title: String,
        val url: String,
        val urlToImage: String
    )

}