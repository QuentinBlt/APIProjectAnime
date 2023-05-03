package com.example.apiproject.repository

import android.content.Context
import androidx.room.Room
import com.example.apiproject.AppDatabase
import com.example.apiproject.data.DBQuote
import com.example.apiproject.data.Quote
import com.example.apiproject.repository.abs.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class QuoteRepository {
     fun getQuote(context: Context): Quote? {
        try{
            val retrofit = Retrofit.Builder()
                .baseUrl("https://animechan.vercel.app/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service: ApiService = retrofit.create(ApiService::class.java)
            val call = service.randomQuote()
            val response = call?.execute()
            if (response != null) {
                if(response.isSuccessful){

                    val db = Room.databaseBuilder(
                        context,
                        AppDatabase::class.java, "database-quotes"
                    ).build()

                    if(response.body() != null) {
                        val quote = response.body()
                        val dbQuote = DBQuote(
                            anime = quote?.anime,
                            character = quote?.character, quote = quote?.quote
                        )
                        db.quoteDao().insertAll(dbQuote)
                        return quote
                    }else
                        return Quote()

                }else{
                    return Quote()
                }
            }

            return Quote(anime = "My Hero Acedemia", character = "All Might", quote = "La cavalerie est la !!!")

        }catch (ex: Exception){
            return null
        }
    }
}