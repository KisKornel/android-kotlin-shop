package com.example.shop

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shop.screen.HomeScreen
import com.example.shop.service.ProductViewModel


@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home", builder = {
        composable("home"){ backStackEntry ->
            val productViewModel: ProductViewModel = viewModel(backStackEntry)
            HomeScreen(modifier, navController, productViewModel)
        }
    })
}