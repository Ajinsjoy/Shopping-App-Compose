package com.macom.shoppingapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(val title: String, val route: String, var icon: ImageVector?,var iconSelected: ImageVector?) {

    object Home : NavigationItem(
        title = "Home",
        route = "Home",
        icon = Icons.Outlined.Home,
        iconSelected=Icons.Default.Home
    )

    object Favorite : NavigationItem(
        title = "Favorite",
        route = "Favorite",
        icon = Icons.Outlined.FavoriteBorder,
        iconSelected=Icons.Default.Favorite
    )

    object Settings : NavigationItem(
        title = "Settings",
        route = "Settings",
        icon = Icons.Outlined.Settings,
        iconSelected=Icons.Default.Settings
    )


}