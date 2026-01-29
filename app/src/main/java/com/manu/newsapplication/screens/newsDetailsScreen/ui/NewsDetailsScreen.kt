package com.manu.newsapplication.screens.newsDetailsScreen.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.HideImage
import androidx.compose.material.icons.filled.SaveAlt
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.SubcomposeAsyncImage
import com.manu.newsapplication.newsReponseModel.Results
import com.manu.newsapplication.screens.newsDetailsScreen.DetailsScreenEvents
import com.manu.newsapplication.screens.newsDetailsScreen.DetailsScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsDetailsScreen(
    results: Results,
    viewModel: DetailsScreenViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    val onEvent = viewModel::onEvent

    Scaffold(
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            Row(
                modifier = Modifier.padding(10.dp)
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        TODO()
                    }
                ) {

                }
            }
        },
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight(0.3f)
                            .background(Color.Transparent),
                        contentAlignment = Alignment.TopStart
                    ){

                        SubcomposeAsyncImage(
                         model = results.image_url,
                            contentScale = ContentScale.FillBounds,
                            contentDescription = null,
                            loading = {
                                CircularProgressIndicator()
                            },
                            error = {
                                Icon(Icons.Default.HideImage,null)
                            }
                        )

                        Row(
                            modifier = Modifier
                                .background(Color.Transparent)
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            IconButton(
                                modifier = Modifier.weight(1f),
                                onClick = {

                                }
                            )
                            {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                    contentDescription = null
                                )
                            }

                            IconButton(
                                onClick = {
                                   if(!state.value.isSaved){
                                       onEvent(DetailsScreenEvents.SaveNews(results))
                                   }
                                }
                            ) {
                                Icon(
                                    imageVector = if (state.value.isSaved){
                                        Icons.Default.Check
                                    }else{
                                        Icons.Default.SaveAlt
                                    },
                                    contentDescription = null
                                )
                            }

                            Spacer(modifier = Modifier.width(10.dp))

                            IconButton(
                                onClick = {
                                    if (state.value.isBookMarked){
                                        onEvent(DetailsScreenEvents.RemoveBookMark(results))
                                    }else{
                                        onEvent(DetailsScreenEvents.BookMarkNews(results))
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = if(state.value.isBookMarked){
                                        Icons.Filled.Bookmark
                                    }else{
                                        Icons.Outlined.Bookmark
                                    },

                                    contentDescription = null
                                )
                            }
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        DetailsScreenContent(
            results = results,
            modifier = Modifier.padding(innerPadding),
            state = state.value,
            onEvent = onEvent
        )
    }
}