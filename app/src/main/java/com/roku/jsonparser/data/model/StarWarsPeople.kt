package com.roku.jsonparser.data.model


data class App(
    val id: String,
    val imageUrl: String,
    val name: String,

    )

data class ListOfApps(
    val apps: List<App>
)
