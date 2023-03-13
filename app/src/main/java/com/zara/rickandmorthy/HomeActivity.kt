package com.zara.rickandmorthy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zara.rickandmorthy.navigate.NavDestinations
import com.zara.rickandmorthy.theme.RickAndMorthyApplicationTheme
import com.zara.rickandmorthy.ui.character.ListScreen
import com.zara.rickandmorthy.ui.character.characterKeyId
import com.zara.rickandmorthy.ui.character.detail.DetailScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMorthyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = NavDestinations.LIST_SCREEN,
                    ) {
                        composable(NavDestinations.LIST_SCREEN) {
                            ListScreen(navController)
                        }
                        composable("${NavDestinations.DETAIL_SCREEN}/{$characterKeyId}") {
                            it.arguments?.getString(characterKeyId)?.toInt()?.let { characterId ->
                                DetailScreen(characterId, navController)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RickAndMorthyApplicationTheme {
        ListScreen(
            navController = rememberNavController()
        )
    }
}

