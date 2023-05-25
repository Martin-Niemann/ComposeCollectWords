package com.niemannsolutions.composecollectwords

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.niemannsolutions.composecollectwords.ui.CollectWordsAddScreen
import com.niemannsolutions.composecollectwords.ui.CollectWordsResultScreen
import com.niemannsolutions.composecollectwords.ui.CollectWordsViewModel

enum class CollectWordsScreen(@StringRes val title: Int) {
    Start(title = R.string.start),
    Result(title = R.string.result)
}

@Composable
fun CollectWordsAppBar(
    currentScreen: CollectWordsScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
fun CollectWordsApp(
    viewModel: CollectWordsViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = CollectWordsScreen.valueOf(
        backStackEntry?.destination?.route ?: CollectWordsScreen.Start.name
    )

    Scaffold(
        topBar = {
            CollectWordsAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        val wordsState by viewModel.wordsState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = CollectWordsScreen.Start.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = CollectWordsScreen.Start.name) {
                CollectWordsAddScreen(
                    words = wordsState.words,
                    addWord = { viewModel.addWord(it) },
                    clearWords = { viewModel.clearWords() },
                    removeWord = { viewModel.removeWord(it) },
                    onNextButtonClicked = {navController.navigate(CollectWordsScreen.Result.name)}
                )
            }
            composable(route = CollectWordsScreen.Result.name) {
                CollectWordsResultScreen(
                    words = wordsState.words,
                    removeWord = { viewModel.removeWord(it) }
                )
            }
        }
    }
}