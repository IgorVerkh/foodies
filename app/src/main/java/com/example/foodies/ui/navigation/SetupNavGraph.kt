package com.example.foodies.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.foodies.ui.screens.catalogue.CatalogueScreen
import com.example.foodies.ui.SplashScreen
import com.example.foodies.ui.screens.product_details.ProductDetailsScreen
import com.example.foodies.ui.screens.shopping_cart.ProductCartScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screen.Catalogue.route) {
            CatalogueScreen(navController = navController)
        }
        composable(route = "${Screen.ProductDetails.route}/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            })) {
            ProductDetailsScreen(it.arguments!!.getInt("id"), navController = navController)
        }
        composable(route = Screen.ProductCart.route) {
            ProductCartScreen(navController = navController)
        }
    }
}