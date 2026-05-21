package com.example.kursyt_walut.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val title: String, val icon: ImageVector) {
    object Home : BottomNavItem("home", "Kursy", Icons.Default.Home)
    object Favorites : BottomNavItem("fav", "Ulubione", Icons.Default.Favorite)
    object Settings : BottomNavItem("settings", "Ustawienia", Icons.Default.Settings)
}