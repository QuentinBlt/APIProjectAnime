package com.example.apiproject.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.apiproject.data.DBQuote

@Dao
interface QuoteDao {
    @Insert
    fun insertAll(vararg quote: DBQuote)
}