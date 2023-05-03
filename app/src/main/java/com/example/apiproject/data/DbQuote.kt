package com.example.apiproject.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DBQuote(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "anime") val anime: String?,
    @ColumnInfo(name = "character") val character: String?,
    @ColumnInfo(name = "quote") val quote: String?,
)