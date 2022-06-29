package com.macom.shoppingapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.macom.shoppingapp.navigation.BottomNavigationBar
import com.macom.shoppingapp.navigation.NavigationItem
import com.macom.shoppingapp.presentation.favorite.FavoriteScreen
import com.macom.shoppingapp.presentation.homeScreen.Home

import com.macom.shoppingapp.presentation.homeScreen.HomeViewModel
import com.macom.shoppingapp.presentation.homeScreen.bottomsheet.BottomSheetContent
import com.macom.shoppingapp.presentation.homeScreen.bottomsheet.BottomSheetContent2
import com.macom.shoppingapp.presentation.settings.SettingsScreen
import com.macom.shoppingapp.ui.theme.ShoppingAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {

            val modalBottomSheetState =
                rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
            val sheetContentState = remember {
                mutableStateOf(0)
            }
            val scope = rememberCoroutineScope()
            ModalBottomSheetLayout(
                sheetContent = {
                    when(sheetContentState.value){
                        0 ->  BottomSheetContent()
                        1 ->  BottomSheetContent2()
                    }

                },
                sheetState = modalBottomSheetState,
                sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                sheetBackgroundColor = colorResource(id = R.color.white),
            ) {
                ShoppingAppTheme {
                    val navController = rememberNavController()
                    Scaffold(
                        bottomBar = {
                            val backStackEntry = navController.currentBackStackEntryAsState()
                            val viewModel: HomeViewModel = hiltViewModel()
                            val state = viewModel.mainUiState
                            if (!state.value.loading) {
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
                                Home(navController, scope, modalBottomSheetState, sheetContentState )
                            }
                            composable(NavigationItem.Favorite.route) {
                                FavoriteScreen(navController)
                            }
                            composable(NavigationItem.Settings.route) {
                                SettingsScreen()
                            }
                        }
                    }
                }
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {


}