package com.macom.shoppingapp.presentation.homeScreen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.macom.shoppingapp.domian.model.Category

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Category(modifier: Modifier=Modifier, categoryItem: List<Category>) {



    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        categoryItem.let {
            LazyRow {
                items(it) { data ->

                    Column(
                        modifier = Modifier,
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Card(
                            modifier = Modifier
                                .padding(start = 16.dp, end = 8.dp),
                            shape = RoundedCornerShape(42.dp)

                        ) {
                            Image(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .size(48.dp)
                                    .clip(CircleShape),
                                painter = rememberAsyncImagePainter(model = data.image),
                                contentDescription = "walletIcon",
                                contentScale = ContentScale.Fit,
                                // crop the image if it's not a square


                                // add a border (optional)
                            )
                        }

                        Text(
                            modifier = Modifier.padding(start = 16.dp, end = 8.dp, top = 8.dp),
                            text = data.name,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )

                    }
                    
                }
            }
            
        }
    }
    
}

@Preview
@Composable
fun PreviewCategory() {
//    Category(Modifier, data)
}