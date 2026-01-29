package org.example.pantrywisecmp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import org.example.pantrywisecmp.product.presentation.navigation.ProductGraphRoutes
import org.example.pantrywisecmp.product.presentation.navigation.productGraph

@Composable
fun NavigationRoot() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ProductGraphRoutes.Graph
    ) {
        productGraph(navController = navController)
    }
}