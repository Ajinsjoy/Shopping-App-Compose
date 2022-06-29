package com.macom.shoppingapp.presentation.homeScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.macom.shoppingapp.presentation.homeScreen.component.*
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Home(
    navController: NavController,
    scope: CoroutineScope,
    modalBottomSheetState: ModalBottomSheetState,
    bottomSheetState: MutableState<Int>
) {
    val viewModel: HomeViewModel = hiltViewModel()
    val state = viewModel.mainUiState
    val scrollState = rememberScrollState()


    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),

        ) {
        if (state.value.loading) {
            CustomCircleProgressBar(Modifier.align(Alignment.Center))
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()

            ) {
                ToolBar(Modifier)
                Column(modifier = Modifier
                    .fillMaxHeight()
                    .verticalScroll(scrollState)) {
                    Category(modifier = Modifier, state.value.categories)
                    Banner(Modifier, state.value.banners)
                    Product(Modifier, state.value.products, false,scope,modalBottomSheetState,bottomSheetState)
//                    Banner(Modifier, state.value.banners)
                    Spacer(modifier = Modifier.padding(bottom = 16.dp))
                }
            }

        }


    }

}


