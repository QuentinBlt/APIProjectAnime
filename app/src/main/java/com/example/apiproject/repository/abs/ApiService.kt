package com.example.apiproject.repository.abs

import com.example.apiproject.data.Quote
import retrofit2.Call
import retrofit2.http.GET


interface ApiService {
    @GET("random")
    fun randomQuote(): Call<Quote?>?
}