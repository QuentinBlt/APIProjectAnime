package com.example.apiproject.repository.abs

import com.example.apiproject.data.Quote
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {
    @GET("/random")
    fun randomQuote(): Call<Quote?>?
}