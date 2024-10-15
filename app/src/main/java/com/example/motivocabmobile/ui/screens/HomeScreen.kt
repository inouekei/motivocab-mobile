package com.example.motivocabmobile.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.motivocabmobile.R
import com.example.motivocabmobile.model.Word
import com.example.motivocabmobile.ui.AppViewModelProvider
import com.example.motivocabmobile.MotivocabTopAppBar
import com.example.motivocabmobile.ui.navigation.NavigationDestination

object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.app_name
}

@Composable
fun HomeScreen(
    listViewModel: ListViewModel = viewModel(factory = AppViewModelProvider.Factory),
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    val listUiState = listViewModel.listUiState
    when (listUiState) {
        is ListUiState.Loading ->
            LoadingScreen(modifier = Modifier.fillMaxSize())
        is ListUiState.Success -> ResultScreen(
            listUiState.list, modifier.padding(top = contentPadding.calculateTopPadding()))
        is ListUiState.Error -> ErrorScreen(modifier = Modifier.fillMaxSize())
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { MotivocabTopAppBar(scrollBehavior = scrollBehavior) }
    ) { Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier.padding(paddingValues = it),
        ) {
            Text(text = "\uD83D\uDD04")
        }

    }}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { MotivocabTopAppBar(scrollBehavior = scrollBehavior) }
    ) { Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier.padding(paddingValues = it),
        ) {
            Text(text = "\uD83D\uDEAB")
        }
    }}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen(lists: List<Word>, modifier: Modifier = Modifier) {
    var cardMap by remember { mutableStateOf(lists.random())}
    var isFrontShown by remember { mutableStateOf(true) }
    val shownText: String =
        if (isFrontShown) cardMap.front
        else cardMap.back

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { MotivocabTopAppBar(scrollBehavior = scrollBehavior) }
    ) { Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier.padding(paddingValues = it),
        ) {
            Text(text = "Success: ${lists.size} words retrieved")
        }

        Column(
            modifier = modifier.padding(paddingValues = it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {isFrontShown = !isFrontShown}) {
                Text(shownText)
            }
            Button(onClick = {
                isFrontShown = true
                cardMap = lists.random()
            }) {
                Text(stringResource(R.string.next))
            }
        }
    }}
}

//@Preview(showBackground = true)
//@Composable
//fun ResultScreenPreview() {
//    MotivocabMobileTheme{
//        ResultScreen(listOf(
//            Word(front = "🍙", back = "onigiri"),
//            Word(front = "🥘", back = "paeria"),
//            Word(front = "🍔", back = "hanbaagaa"),
//        ))
//    }
//}

//fun shuffleCards(wordList: List<Word>): Word {
////    return listOf(
////        Word( "🍙", "onigiri"),
////        Word("🥘", "paeria"),
////        Word("🍔", "hanbaagaa"),
////    ).random()
//    return wordList.random()
//}