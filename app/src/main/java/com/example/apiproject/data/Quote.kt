package com.example.apiproject.data

import com.google.gson.annotations.SerializedName

data class Quote(
    @SerializedName("anime")
    var anime: String? = null,
    @SerializedName("character")
    var character: String? = null,
    @SerializedName("quote")
    var quote: String? = null
)
