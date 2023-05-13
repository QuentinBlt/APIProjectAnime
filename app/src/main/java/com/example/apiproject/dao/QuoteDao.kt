package com.example.apiproject.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.apiproject.data.DBQuote

@Dao
interface QuoteDao {
    @Insert
    fun insertAll(vararg quote: DBQuote)

    @Query("SELECT * FROM DBQuote ORDER BY uid DESC")
    fun getAllQuotesByDesc(): List<DBQuote>
}