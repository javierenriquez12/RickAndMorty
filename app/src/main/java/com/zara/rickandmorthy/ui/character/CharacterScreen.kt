package com.zara.rickandmorthy.ui.character

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.zara.rickandmorthy.R
import com.zara.rickandmorthy.core.AppComposable
import com.zara.rickandmorthy.navigate.NavDestinations
import com.zara.rickandmorthy.theme.RickAndMorthyApplicationTheme

const val characterKeyId = "characterId"

@Composable
fun ListScreen(
    navController: NavController, viewModel: CharacterViewModel = hiltViewModel()
) {
    viewModel.fetchCharacters()
    AppComposable(
        viewModel = viewModel,
        content = { ListCharacters(navController, viewModel) })
}


@Composable
fun ListCharacters(
    navController: NavController,
    viewModel: CharacterViewModel
) {
    val characterList = viewModel.fetchCharacters.observeAsState().value ?: emptyList()
    Scaffold(
        topBar = { SearchAppBar(viewModel = viewModel) }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.background)
        ) {
            Image(
                painter = painterResource(R.drawable.background_character),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
        }
        Column {
            Text(
                text = "Characters",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(8.dp)
            )
            LazyColumn {
                items(characterList) { character ->
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate("${NavDestinations.DETAIL_SCREEN}/${character.id}")
                            }
                    ) {
                        Column {
                            Image(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(16f / 9f),
                                painter = rememberImagePainter(
                                    ImageRequest.Builder(
                                        LocalContext.current
                                    ).data(data = character.image)
                                        .apply(block = fun ImageRequest.Builder.() {
                                            placeholder(R.drawable.placeholder)
                                            error(R.drawable.placeholder)
                                        }).build()
                                ),
                                contentDescription = null,
                                contentScale = ContentScale.FillWidth
                            )
                            Column(Modifier.padding(8.dp)) {
                                Text(
                                    character.name ?: "",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 2
                                )
                            }
                        }
                    }
                }

            }
        }

    }
}

@Composable
private fun SearchAppBar(
    viewModel: CharacterViewModel,
) {
    var query: String by rememberSaveable { mutableStateOf("") }

    TextField(
        value = query,
        onValueChange = { onQueryChanged ->
            query = onQueryChanged
            viewModel.performQuery(onQueryChanged)
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.Search,
                tint = MaterialTheme.colors.onBackground,
                contentDescription = "Search Icon"
            )
        },
        trailingIcon = {
            IconButton(onClick = { query = "" }) {
                Icon(
                    imageVector = Icons.Rounded.Clear,
                    tint = MaterialTheme.colors.onBackground,
                    contentDescription = "Clear Icon"
                )
            }
        },
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
        placeholder = { Text(text = stringResource(R.string.hint_search_query)) },
        textStyle = MaterialTheme.typography.subtitle1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.background, shape = RectangleShape)
    )
}


@Preview(showBackground = true)
@Composable
fun ListPreview() {
    RickAndMorthyApplicationTheme() {
        ListScreen(
            navController = rememberNavController()
        )
    }
}
