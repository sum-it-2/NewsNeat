package com.example.newsneat.screens.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.newsneat.MyViewModel.MyViewModel
import com.example.newsneat.screens.DetailScreen
import com.example.newsneat.screens.HomeUi


@Composable
fun NavApp(viewModel: MyViewModel, modifier: Modifier, padding: androidx.compose.foundation.layout.PaddingValues) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.MainScreenRoutes) {

        composable<Routes.MainScreenRoutes>{
            HomeUi(viewModel = viewModel, navController = navController, padding = padding)
        }

        composable<Routes.DetailScreenRoutes>{

            val data = it.toRoute<Routes.DetailScreenRoutes>()

            DetailScreen(
                content = data.content,
                description = data.description,
                publishedAt = data.publishedAt,
                title = data.title,
                url = data.url,
                urlToImage = data.urlToImage,
                padding = padding
            )
        }
    }

}