package com.example.kursyt_walut.navigation
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.kursyt_walut.ui.DetailsScreen
import com.example.kursyt_walut.ui.FavoritesScreen
import com.example.kursyt_walut.ui.HomeScreen
import com.example.kursyt_walut.ui.SettingsScreen


@Composable
fun CurrencyApp() {
    val navController = rememberNavController()
    val items = listOf(BottomNavItem.Home, BottomNavItem.Favorites, BottomNavItem.Settings)

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                items.forEach { item ->
                    NavigationBarItem(
                        icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                        label = { Text(text = item.title) },
                        selected = currentRoute == item.route,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            startDestination = BottomNavItem.Home.route)
        {
            composable(BottomNavItem.Home.route) { HomeScreen() }
            composable("details") {DetailsScreen()}
            composable(BottomNavItem.Favorites.route) { FavoritesScreen() }
            composable(BottomNavItem.Settings.route) { SettingsScreen() }
        }
    }
}