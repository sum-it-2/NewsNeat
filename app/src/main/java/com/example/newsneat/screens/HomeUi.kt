package com.example.newsneat.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.internal.view.SupportMenuItem
import androidx.navigation.NavController
import coil3.compose.SubcomposeAsyncImage
import com.example.newsneat.MyViewModel.MyViewModel
import com.example.newsneat.screens.nav.Routes


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeUi(
    viewModel: MyViewModel,
    navController: NavController,
    padding: PaddingValues
) {

    val response = viewModel.response.value?.articles ?: emptyList()
    val isLoading by viewModel.isLoading.collectAsState()




    Column(modifier = Modifier.fillMaxSize()) {

        SearchBar(viewModel = viewModel)

        if (isLoading && response.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (response.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("No news available. Try a different search.")
            }
        } else {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
            items(response) {
                Card(
                    shape = CardDefaults.elevatedShape,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 16.dp),
                    onClick = {
                        navController.navigate(
                            Routes.DetailScreenRoutes(
                                content = it.content,
                                description = it.description,
                                publishedAt = it.publishedAt,
                                title = it.title,
                                url = it.url,
                                urlToImage = it.urlToImage
                            )
                        )
                    }
                ) {
                    SubcomposeAsyncImage(
                        model = it.urlToImage,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp),
                        contentScale = ContentScale.Crop,
                        loading = {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    )
                    Text(
                        text = it.title,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(8.dp)
                    )
                }

            }
        }}
    }
}

@Composable
fun SearchBar(viewModel: MyViewModel) {

    val query = remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Row(
        modifier = Modifier
            .background(Color.Red)
            .padding(top = 35.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "My\nNews",
            fontSize = 24.sp,
            color = Color.White,
            modifier = Modifier.padding(start = 16.dp)
        )
        OutlinedTextField(
            value = query.value,
            onValueChange = {
                query.value = it
            },
            shape = CircleShape,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.DarkGray,
                unfocusedBorderColor = Color.LightGray,
                focusedContainerColor = Color.LightGray,
                unfocusedContainerColor = Color.White,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.DarkGray
            ),
            keyboardActions = KeyboardActions(onDone = {
                viewModel.fetchNews(query.value)
                keyboardController?.hide()
                focusManager.clearFocus()
            }),
            placeholder = { Text(text = "Search") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            singleLine = true,
            trailingIcon = {
                Icon(
                    Icons.Default.Search,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable {
                            viewModel.fetchNews(query.value)
                            keyboardController?.hide()
                            focusManager.clearFocus()
                        })
            })
    }

}