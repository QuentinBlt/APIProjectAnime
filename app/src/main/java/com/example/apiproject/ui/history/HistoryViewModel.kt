package com.example.apiproject.ui.history

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apiproject.data.DBQuote
import com.example.apiproject.repository.QuoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor() : ViewModel() {

    private val _quotesHistory = MutableLiveData<List<DBQuote>>(emptyList())
    val quotesHistory : LiveData<List<DBQuote>>
        get() = _quotesHistory

    fun getQuotesHistory(context: Context){
        val scope = CoroutineScope(Job() + Dispatchers.IO)
        scope.launch {
            try{
                val quotes = QuoteRepository().getQuoteHistory(context)
                _quotesHistory.postValue(quotes)
            }catch (ex: Exception){
                _quotesHistory.postValue(emptyList())
            }
        }.start()
    }

}