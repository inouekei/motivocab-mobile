package com.example.motivocabmobile.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.motivocabmobile.R
import com.example.motivocabmobile.model.Word
import com.example.motivocabmobile.ui.theme.MotivocabMobileTheme

@Composable
fun HomeScreen(
    listUiState: ListUiState,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    when (listUiState) {
        is ListUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
        is ListUiState.Success -> ResultScreen(
            listUiState.list, modifier.padding(top = contentPadding.calculateTopPadding()))
        is ListUiState.Error -> ErrorScreen(modifier = Modifier.fillMaxSize())
    }
}
@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier,
    ) {
        Text(text = "\uD83D\uDD04")
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier,
    ) {
        Text(text = "\uD83D\uDEAB")
    }
}

@Composable
fun ResultScreen(lists: List<Word>, modifier: Modifier = Modifier) {
//    var cardFrontString = stringResource(R.string.card_front)
//    var cardBackString = stringResource(R.string.card_back)
//    var frontInitialString = "front"
//    var backInitialString = "back"
    //    var frontInitialString = stringResource(R.string.loading)
    //    var backInitialString = frontInitialString
    var cardMap by remember { mutableStateOf(shuffleCards(lists))}
//    var cardMap by remember { mutableStateOf(Word(
//        frontInitialString, backInitialString
//    ))}
    var isFrontShown by remember { mutableStateOf(true) }
    val shownText: String =
        if (isFrontShown) cardMap.front
        else cardMap.back

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier,
    ) {
        Text(text = "Success: ${lists.size} words retrieved")
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {isFrontShown = !isFrontShown}) {
            Text(shownText)
        }
        Button(onClick = {
            isFrontShown = true
            cardMap = shuffleCards(lists)
//            cardMap = shuffleCards(cardFrontString, cardBackString)
        }) {
            Text(stringResource(R.string.next))
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    MotivocabMobileTheme{
        ResultScreen(listOf(
            Word(front = "🍙", back = "onigiri"),
            Word(front = "🥘", back = "paeria"),
            Word(front = "🍔", back = "hanbaagaa"),
        ))
    }
}

fun shuffleCards(wordList: List<Word>): Word {
//    return listOf(
//        Word( "🍙", "onigiri"),
//        Word("🥘", "paeria"),
//        Word("🍔", "hanbaagaa"),
//    ).random()
    return wordList.random()
}