package com.example.motivocabmobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.motivocabmobile.network.ListApi
import com.example.motivocabmobile.ui.MotivocabMobileApp
import com.example.motivocabmobile.ui.theme.MotivocabMobileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MotivocabMobileTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Motivocab(
                        name = "Android",
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    )
                }
//                Surface(modifier = Modifier.fillMaxSize()) {
//                    MotivocabMobileApp()
//                }
            }
        }
    }
}

@Composable
fun Motivocab(name: String, modifier: Modifier = Modifier
    .fillMaxSize()
    .wrapContentSize(Alignment.Center)
) {
    var cardFrontString = stringResource(R.string.card_front)
    var cardBackString = stringResource(R.string.card_back)
    var frontInitialString = "front"
    var backInitialString = "back"
//    var frontInitialString = stringResource(R.string.loading)
//    var backInitialString = frontInitialString
    var cardMap by remember { mutableStateOf(mapOf(
        cardFrontString to frontInitialString,
        cardBackString to backInitialString
    ))}
    var isFrontShown by remember {mutableStateOf(true)}
    val shownText: String =
        if (isFrontShown) cardMap[cardFrontString].toString()
        else cardMap[cardBackString].toString()

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Button(onClick = {isFrontShown = !isFrontShown}) {
            Text(shownText)
        }
        Button(onClick = {
            isFrontShown = true
            cardMap = shuffleCards(cardFrontString, cardBackString)
        }) {
            Text(stringResource(R.string.next))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MotivocabApp() {
    MotivocabMobileTheme {
        Motivocab("Android")
    }
}

fun shuffleCards(cardFrontString: String, cardBackString: String): Map<String, String> {
    return arrayOf(
        mapOf(cardFrontString to "🍙", cardBackString to "onigiri"),
        mapOf(cardFrontString to "🥘", cardBackString to "paeria"),
        mapOf(cardFrontString to "🍔", cardBackString to "hanbaagaa")
    ).random()
}