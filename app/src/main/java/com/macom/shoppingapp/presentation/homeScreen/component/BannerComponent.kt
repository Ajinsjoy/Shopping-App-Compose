package com.macom.shoppingapp.presentation.homeScreen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.macom.shoppingapp.domian.model.Banner

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Banner(modifier: Modifier, data: List<Banner>) {


    Column(
        modifier = modifier.padding(top = 8.dp)

    ) {
        data.let {
            LazyRow {
                items(it) { banner ->
                    Box(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
                        Card(
                            modifier = Modifier
                                .height(180.dp)
                                .width(320.dp)

                        ) {
                            Image(
                                modifier = Modifier.fillMaxSize(),
                                painter = rememberAsyncImagePainter(model = banner.image),
                                contentDescription = "walletIcon",
                                contentScale = ContentScale.Crop,

                                )

                        }
                    }


                }
            }
        }


    }

}