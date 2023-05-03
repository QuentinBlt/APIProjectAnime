package com.example.apiproject

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.apiproject.dao.QuoteDao
import com.example.apiproject.data.DBQuote

@Database(entities = [DBQuote::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun quoteDao(): QuoteDao
}