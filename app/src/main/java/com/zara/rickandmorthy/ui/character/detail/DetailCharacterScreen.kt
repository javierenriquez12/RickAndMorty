package com.zara.rickandmorthy.ui.character.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.zara.domain.model.Character
import com.zara.rickandmorthy.core.AppComposable
import com.zara.rickandmorthy.theme.RickAndMorthyApplicationTheme

@Composable
fun DetailScreen(
    characterId: Int,
    navController: NavController,
    viewModel: DetailCharacterViewModel = hiltViewModel()
) {
    val character by viewModel.character.observeAsState(initial = null)
    val showSmartMode by viewModel.showSmartMode.observeAsState(initial = true)
    viewModel.getNewsById(characterId)
    AppComposable(
        viewModel = viewModel,
        content = {
            DetailScreen(
                navController,
                character,
                showSmartMode
            )
        }
    )
}

@Composable
fun DetailScreen(
    navController: NavController,
    character: Character?,
    showSmartMode: Boolean
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        character?.name ?: "",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                }
            )
        }
    ) {
        character?.let {
            if (showSmartMode) {
                SmartView(it)
            }
        } ?: run {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DetailsPreview() {
    RickAndMorthyApplicationTheme {
        DetailScreen(
            navController = rememberNavController(),
            character = null,
            showSmartMode = true
        )
    }
}