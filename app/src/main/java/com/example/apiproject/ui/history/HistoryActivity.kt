package com.example.apiproject.ui.history

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.apiproject.R
import com.example.apiproject.data.DBQuote
import com.example.apiproject.ui.theme.APIProjectTheme

class HistoryActivity : ComponentActivity() {

    private val historyViewModel: HistoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            historyViewModel.getQuotesHistory(this)
        }

        setContent {
            APIProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val quotes by historyViewModel.quotesHistory.observeAsState()
                    Screen(quotes ?: emptyList())
                }
            }
        }
    }
}

@Composable
fun Screen(quotes: List<DBQuote>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
            .padding(start = 10.dp, end = 10.dp)
    ) {
        items(quotes) { quote ->
            QuoteItem(quote = quote)
        }
    }
}

@Composable
fun QuoteItem(quote: DBQuote) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "\"${quote.quote ?: String()}\"",
            fontStyle = FontStyle.Italic,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "${stringResource(id = R.string.title_text)} : ${quote.anime ?: String()}",
            style = MaterialTheme.typography.bodySmall
        )
        Text(
            text = "${stringResource(id = R.string.character_text)} : ${quote.character ?: String()}",
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(modifier = Modifier.height(15.dp))
        Divider(thickness = 1.dp, color = Color.LightGray)
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    APIProjectTheme {
        val quotes = ArrayList<DBQuote>()
        quotes.add(DBQuote(0, "My Hero Academia", "All Might", "La cavallerie est la !"))
        quotes.add(DBQuote(0, "My Hero Academia", "All Might", "La cavallerie est la !"))
        quotes.add(DBQuote(0, "My Hero Academia", "All Might", "La cavallerie est la !"))
        quotes.add(DBQuote(0, "My Hero Academia", "All Might", "La cavallerie est la !"))
        quotes.add(DBQuote(0, "My Hero Academia", "All Might", "La cavallerie est la !"))
        quotes.add(DBQuote(0, "My Hero Academia", "All Might", "La cavallerie est la !"))
        quotes.add(DBQuote(0, "My Hero Academia", "All Might", "La cavallerie est la !"))
        quotes.add(DBQuote(0, "My Hero Academia", "All Might", "La cavallerie est la !"))
        quotes.add(DBQuote(0, "My Hero Academia", "All Might", "La cavallerie est la !"))
        quotes.add(DBQuote(0, "My Hero Academia", "All Might", "La cavallerie est la !"))
        quotes.add(DBQuote(0, "My Hero Academia", "All Might", "La cavallerie est la !"))
        quotes.add(DBQuote(0, "My Hero Academia", "All Might", "La cavallerie est la !"))
        quotes.add(DBQuote(0, "My Hero Academia", "All Might", "La cavallerie est la !"))
        quotes.add(DBQuote(0, "My Hero Academia", "All Might", "La cavallerie est la !"))
        quotes.add(DBQuote(0, "My Hero Academia", "All Might", "La cavallerie est la !"))
        quotes.add(DBQuote(0, "My Hero Academia", "All Might", "La cavallerie est la !"))
        quotes.add(DBQuote(0, "My Hero Academia", "All Might", "La cavallerie est la !"))
        quotes.add(DBQuote(0, "My Hero Academia", "All Might", "La cavallerie est la !"))
        quotes.add(DBQuote(0, "My Hero Academia", "All Might", "La cavallerie est la !"))
        quotes.add(DBQuote(0, "My Hero Academia", "All Might", "La cavallerie est la !"))
        Screen(quotes.toList())
    }
}

@Preview(showBackground = true)
@Composable
fun QuoteItemPreview() {
    APIProjectTheme {
        QuoteItem(DBQuote(0, "My Hero Academia", "All Might", "La cavallerie est la !"))
    }
}