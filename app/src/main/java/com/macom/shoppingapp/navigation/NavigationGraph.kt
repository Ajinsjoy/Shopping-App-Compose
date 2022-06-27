package com.macom.shoppingapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.macom.shoppingapp.presentation.favorite.FavoriteScreen
import com.macom.shoppingapp.presentation.homeScreen.Home

@Composable
fun NavigationGraph(navController: NavHostController, modifier: Modifier) {
//    NavHost(navController, startDestination = NavigationItem.Home.route, modifier = modifier) {
//        composable(route= NavigationItem.Home.route) {
//            Home(navController)
//        }
//        composable(NavigationItem.Favorite.route) {
//            FavoriteScreen(navController)
//        }
//    }
}

@Composable
fun BottomNavigationBar(
    navController: NavController,
    modifier: Modifier
) {

    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Favorite,
        NavigationItem.Settings
    )

    val backStackEntry = navController.currentBackStackEntryAsState()
    NavigationBar(modifier = modifier) {
        items.forEach { item ->

            val selected = item.route == backStackEntry.value?.destination?.route
            NavigationBarItem(
                selected = selected,
                onClick = {
//                    NavigationItem.Home.icon =
//                        if (item == NavigationItem.Home) Icons.Default.Home else Icons.Outlined.Home
//                    NavigationItem.Favorite.icon =
//                        if (item == NavigationItem.Favorite) Icons.Default.Favorite else Icons.Outlined.FavoriteBorder
//                    NavigationItem.Settings.icon =
//                        if (item == NavigationItem.Settings) Icons.Default.Settings else Icons.Outlined.Settings

                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true

                    }
                },
                icon = {
//                    NavigationItem.Home.icon =
//                        if (item == NavigationItem.Home) Icons.Default.Home else Icons.Outlined.Home
//                    NavigationItem.Favorite.icon =
//                        if (item == NavigationItem.Favorite) Icons.Default.Favorite else Icons.Outlined.FavoriteBorder
//                    NavigationItem.Settings.icon =
//                        if (item == NavigationItem.Settings) Icons.Default.Settings else Icons.Outlined.Settings

                    item.icon?.let {
                        (if (selected) item.iconSelected else item.icon)?.let { icon ->
                            Icon(
                                imageVector = icon,
                                contentDescription = item.title
                            )
                        }
                    }
                },

                label = {
                    Text(text = item.title)
                },
                alwaysShowLabel = false
            )

        }
    }

}


