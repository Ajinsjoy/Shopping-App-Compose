package com.macom.shoppingapp.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    var title: String,
    var icon: ImageVector,
    var route: String,
    val badgeCount: Int = 0
)