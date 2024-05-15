package com.example.foodies.ui.navigation

sealed class Screen(val route: String) {
    data object Splash : Screen("splash_screen")
    data object Catalogue : Screen("catalogue")
    data object ProductDetails : Screen("product_details")
    data object ProductCart : Screen("product_cart")
}