package com.example.apiproject.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.apiproject.R
import com.example.apiproject.data.Quote
import com.example.apiproject.ui.history.HistoryActivity
import com.example.apiproject.ui.theme.APIProjectTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            APIProjectTheme {
                val quote by mainViewModel.currentQuote.observeAsState()
                val waitNewQuote by mainViewModel.waitNewQuote.observeAsState()
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Screen(quote ?: Quote(),
                        waitNewQuote ?: false,
                        onRefreshButtonClick = { onRefreshButtonClick() },
                        onHistoryButtonClick = { onHistoryButtonClick() })
                }
            }
        }
    }

    private fun onRefreshButtonClick(){
        mainViewModel.getNewQuote(this)
    }

    private fun onHistoryButtonClick(){
        startActivity(Intent(this, HistoryActivity::class.java))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Screen(quote: Quote, waitNewQuote: Boolean, onRefreshButtonClick: () -> Unit, onHistoryButtonClick: () -> Unit){
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onHistoryButtonClick.invoke()
            }) {
                Icon(painter = painterResource(id = R.drawable.baseline_history_24), String())
            }
        }
    ) {
        Quote(quote, waitNewQuote,  onRefreshButtonClick)
    }
}

@Composable
fun Quote(quote: Quote, waitNewQuote: Boolean, onRefreshClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "\"${quote.quote ?: String()}\"",
            fontStyle = FontStyle.Italic,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "${stringResource(id = R.string.title_text)} : ${quote.anime ?: String()}",
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "${stringResource(id = R.string.character_text)} : ${quote.character ?: String()}",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(45.dp))
        Button(
            onClick = { onRefreshClick.invoke() },
            enabled = !waitNewQuote) {
            Box(
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(5.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center
                ) {
                    if(!waitNewQuote) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_format_quote_24),
                            contentDescription = String()
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                    } else {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            color = Color.Red,
                            strokeWidth = 3.dp
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                    }
                    Text(
                        text = stringResource(id = R.string.newquote_button),
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    APIProjectTheme {
        Screen(Quote("anime", "hero", "some citation"), false, {}, {})
    }
}

@Preview(showBackground = true)
@Composable
fun QuotePreview() {
    APIProjectTheme {
        Quote(Quote("anime", "hero", "some citation"),false, {})
    }
}