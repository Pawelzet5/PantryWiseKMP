package org.example.pantrywisecmp

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.pantrywise.presentation.components.SpeedDialFab
import kotlinx.coroutines.launch
import org.example.pantrywisecmp.core.presentation.theme.PantryWiseTheme
import org.example.pantrywisecmp.core.presentation.util.ObserveAsEvents
import org.example.pantrywisecmp.core.presentation.util.SnackbarManager
import org.example.pantrywisecmp.product.presentation.components.FancyBottomBar
import org.example.pantrywisecmp.product.presentation.navigation.*
import org.example.pantrywisecmp.product.presentation.navigationBarItems
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

@Composable
fun App() {
    val navController = rememberNavController()
    val currentRoute by navController.currentBackStackEntryAsState()

    val snackbarState = remember { SnackbarHostState() }
    val snackbarManager = koinInject<SnackbarManager>()
    val scope = rememberCoroutineScope()

    ObserveAsEvents(snackbarManager.messages) {
        scope.launch {
            snackbarState.showSnackbar(it.asString())
        }
    }

    PantryWiseTheme {
        Scaffold(
            topBar = {
                currentRoute?.toProductGraphRoute()?.toolbarTextRes()?.let {
                    MainTopBar(
                        showBackButton = currentRoute?.toProductGraphRoute()?.showBackButton()
                            ?: false,
                        onBackClick = { navController.navigateUp() },
                        text = stringResource(it)
                    )
                }
            },
            floatingActionButton = {
                MainFab(
                    showButton = currentRoute?.toProductGraphRoute()?.showAddButton() ?: false,
                    onClick = { navController.navigate(it) }
                )
            },
            bottomBar = {
                currentRoute?.toProductGraphRoute()?.let { route ->
                    if (route.showBottomNavigationBar()) {
                        FancyBottomBar(
                            currentRoute = route,
                            items = navigationBarItems,
                            onNavigationItemClicked = { navController.navigate(it) },
                            modifier = Modifier.navigationBarsPadding()
                        )
                    }
                }
            },
            snackbarHost = {
                SnackbarHost(snackbarState)
            }
        ) { innerPadding ->
            NavigationRoot(
                navController,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(
    modifier: Modifier = Modifier,
    showBackButton: Boolean,
    onBackClick: () -> Unit,
    text: String
) {
    TopAppBar(
        title = { Text(text = text) },
        modifier = modifier,
        navigationIcon = {
            if (showBackButton)
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
        }
    )
}

@Composable
fun MainFab(
    showButton: Boolean,
    onClick: (ProductGraphRoutes) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    SpeedDialFab(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        },
        onAddManual = {
            expanded = false
            onClick(ProductGraphRoutes.SearchAndSelectProducts)

        },
        onAddCamera = {
            expanded = false
            onClick(ProductGraphRoutes.AddProductsWithCamera)
        },
        visible = showButton
    )
}
