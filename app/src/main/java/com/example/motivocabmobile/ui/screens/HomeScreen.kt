package com.example.motivocabmobile.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.motivocabmobile.R
import com.example.motivocabmobile.model.Word
import com.example.motivocabmobile.ui.AppViewModelProvider
import com.example.motivocabmobile.MotivocabTopAppBar
import com.example.motivocabmobile.ui.navigation.NavigationDestination
import kotlinx.datetime.Instant

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
    val latestListState by listViewModel.latestListState.collectAsState()
//    val lastUpdatedAt by listViewModel.lastUpdatedAt.collectAsState()
    val context = LocalContext.current
    LaunchedEffect (key1 = listUiState) {
        if (listUiState == ListUiState.Loading) {
            Toast.makeText(context, "Updating database", Toast.LENGTH_SHORT).show()
        } else if (listUiState == ListUiState.Success) {
            Toast.makeText(context, "Updated database", Toast.LENGTH_SHORT).show()
        } else if (listUiState ==  ListUiState.Error) {
            Toast.makeText(context, "Failed in Updating database", Toast.LENGTH_SHORT).show()
        }
    }
    if (latestListState.list.size > 0) {
        ResultScreen(
            latestListState.list,
            listViewModel.lastUpdatedAt,
            modifier.padding(top = contentPadding.calculateTopPadding())
        )
    } else {
        ErrorScreen(modifier = Modifier.fillMaxSize())
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
fun ResultScreen(lists: List<Word>, lastUpdatedAt: Instant, modifier: Modifier = Modifier) {
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
        Column(
            modifier = modifier.padding(paddingValues = it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            Button(
                onClick = {isFrontShown = !isFrontShown},
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                ),
                modifier = modifier
                    .size(width = 300.dp, height = 150.dp)
                    .shadow(
                        elevation = 50.dp,
                        spotColor = Color.Gray,
                        ambientColor = Color.Gray
                    )
            ) {
                Text(
                    text = shownText,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp,
                    modifier = Modifier
                )
            }
            Spacer(modifier = Modifier.height(50.dp))
            Button(onClick = {
                isFrontShown = true
                cardMap = lists.random()
            }) {
                Text(stringResource(R.string.next))
            }
        }

        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = modifier.padding(paddingValues = it),
        ) {
            Text(text = "Success: ${lists.size} words retrieved")
//            Text(text = "Last updated at ${lastUpdatedAt.toString()} ")
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