package com.macom.shoppingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.macom.shoppingapp.navigation.BottomNavigationBar
import com.macom.shoppingapp.navigation.NavigationGraph
import com.macom.shoppingapp.navigation.NavigationItem
import com.macom.shoppingapp.presentation.favorite.FavoriteScreen
import com.macom.shoppingapp.presentation.homeScreen.Home

import com.macom.shoppingapp.presentation.homeScreen.HomeViewModel
import com.macom.shoppingapp.presentation.settings.SettingsScreen
import com.macom.shoppingapp.ui.theme.ShoppingAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {

            ShoppingAppTheme {
//              Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
                val navController = rememberNavController()


                Scaffold(
                    bottomBar = {
                        val backStackEntry = navController.currentBackStackEntryAsState()
                        val viewModel: HomeViewModel = hiltViewModel()
                        val state = viewModel.mainUiState
                        if(!state.value.loading){
                            BottomNavigationBar(
                                navController = navController,
                                modifier = Modifier.navigationBarsPadding()
                            )
                        }

                    }
                ) {

                    NavHost(
                        navController,
                        startDestination = NavigationItem.Home.route,
                        modifier = Modifier.padding(it)
                    ) {
                        composable(route = NavigationItem.Home.route) {
                            Home(navController)
                        }
                        composable(NavigationItem.Favorite.route) {
                            FavoriteScreen(navController)
                        }
                        composable(NavigationItem.Settings.route) {
                            SettingsScreen()
                        }
                    }

//                      NavigationGraph(
//                          navController = navController,
//                          modifier = Modifier.padding(it),
//                      )
                }
            }
//            }
        }


    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {


}