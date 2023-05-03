package com.example.apiproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.apiproject.ui.MainViewModel
import com.example.apiproject.data.Quote
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
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Quote(quote ?: Quote()) {
                        onRefreshClick()
                    }
                }
            }
        }
    }

    private fun onRefreshClick(){
        mainViewModel.getNewQuote(this)
    }
}

@Composable
fun Quote(quote: Quote, onRefreshClick: () -> Unit) {
    Column {
        Text(
            text = "Title : ${quote.anime}",
            style = MaterialTheme.typography.bodySmall
        )
        Text(
            text = "Character : ${quote.character}",
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "\"${quote.quote}\"",
            fontStyle = FontStyle.Italic,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(45.dp))
        Button(onClick = { onRefreshClick.invoke() }) {
            Text(text = "New quote")
        }
        Spacer(modifier = Modifier.height(5.dp))
        Button(onClick = { /*TODO*/ }) {
            Text(text = "View old quotes")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    APIProjectTheme {
        Quote(Quote("anime", "hero", "some citation"), { })
    }
}