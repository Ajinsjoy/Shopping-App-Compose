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
            val bottomSheet = "Add Products"
            val bottomSheetState by remember {
                mutableStateOf(bottomSheet)
            }
            val scope = rememberCoroutineScope()
            ModalBottomSheetLayout(
                sheetContent = {
                    if(bottomSheetState == "Add Products")
                        BottomSheetContent()
                    else
                        BottomSheetContent2()
                },
                sheetState = modalBottomSheetState,
                sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                sheetBackgroundColor = colorResource(id = R.color.white),
            ) {
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
                                Home(navController, scope, modalBottomSheetState, bottomSheetState)
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
            }

        }


    }
}

@Composable
fun BottomSheetListItem(icon: ImageVector, title: String, onItemClick: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { onItemClick(title) })
            .height(55.dp)
            .background(color = colorResource(id = R.color.white))
            .padding(start = 15.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = icon, contentDescription = "Share", tint = Color.Black)
        Spacer(modifier = Modifier.width(20.dp))
        Text(text = title, color = Color.Black)
    }
}

@Composable
fun BottomSheetContent() {
    val context = LocalContext.current
    Column(modifier = Modifier.padding(vertical = 32.dp)) {
        BottomSheetListItem(
            icon = Icons.Default.Share,
            title = "Share",
            onItemClick = { title ->
                Toast.makeText(
                    context,
                    title,
                    Toast.LENGTH_SHORT
                ).show()
            })
        BottomSheetListItem(
            icon = Icons.Default.Link,
            title = "Get link",
            onItemClick = { title ->
                Toast.makeText(
                    context,
                    title,
                    Toast.LENGTH_SHORT
                ).show()
            })
        BottomSheetListItem(
            icon = Icons.Default.Edit,
            title = "Edit name",
            onItemClick = { title ->
                Toast.makeText(
                    context,
                    title,
                    Toast.LENGTH_SHORT
                ).show()
            })
        BottomSheetListItem(
            icon = Icons.Default.Delete,
            title = "Delete collection",
            onItemClick = { title ->
                Toast.makeText(
                    context,
                    title,
                    Toast.LENGTH_SHORT
                ).show()
            })
    }
}



@Composable
fun BottomSheetContent2() {
    val context = LocalContext.current
    Column(modifier = Modifier.padding(vertical = 32.dp)) {
        BottomSheetListItem(
            icon = Icons.Default.Share,
            title = "Share",
            onItemClick = { title ->
                Toast.makeText(
                    context,
                    title,
                    Toast.LENGTH_SHORT
                ).show()
            })
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {


}