@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.motivocabmobile

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.motivocabmobile.ui.navigation.ListNavHost
import com.example.motivocabmobile.ui.theme.MotivocabMobileTheme


@Composable
fun MotivocabMobileApp(navController: NavHostController = rememberNavController()) {
    ListNavHost(navController = navController)
}

@Composable
fun MotivocabTopAppBar(scrollBehavior: TopAppBarScrollBehavior, modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.headlineSmall
            )
        },
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun MotivocabApp() {
    MotivocabMobileTheme {
        MotivocabMobileApp()
    }
}

