package com.example.apiproject.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apiproject.data.Quote
import com.example.apiproject.repository.QuoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _currentQuote = MutableLiveData<Quote>()
    val currentQuote: LiveData<Quote>
        get() = _currentQuote

    fun getNewQuote(context: Context){
        val scope = CoroutineScope(Job() + Dispatchers.IO)
        scope.launch {
            val quote = QuoteRepository().getQuote(context)
            _currentQuote.postValue(quote)
        }.start()
    }

}