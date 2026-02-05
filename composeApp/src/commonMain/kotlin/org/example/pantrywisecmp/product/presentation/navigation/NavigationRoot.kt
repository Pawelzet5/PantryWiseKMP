package org.example.pantrywisecmp.product.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost


@Composable
fun NavigationRoot(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = ProductGraphRoutes.Graph,
        modifier = modifier
    ) {
        productGraph(navController)
    }
}