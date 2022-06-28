package com.macom.shoppingapp.presentation.favorite

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.macom.shoppingapp.domian.model.Product
import com.macom.shoppingapp.presentation.homeScreen.component.Product
import com.macom.shoppingapp.presentation.homeScreen.component.ToolBar

@Composable
fun FavoriteScreen(navController: NavHostController) {
    val viewModel: FavoriteViewModel = hiltViewModel()
    val favItem = viewModel.favoriteUiState.value.products

    Column(modifier = Modifier.statusBarsPadding()) {
        ToolBar(modifier = Modifier)
        LazyVerticalGrid(
            columns = GridCells.Fixed(2)
        ) {
            items(favItem) { data ->

               ProductItem(data, onAddFavoriteClick = { id, status ->
                    viewModel.addFavorite(id, status)

                }, onItemClick = {})
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductItem(
    data: Product,
    onAddFavoriteClick: (Int, Boolean) -> Unit,
    onItemClick: () -> Unit
) {
    val favColor = MaterialTheme.colorScheme.surfaceVariant
    val addFavColor = Color.Red
    var favAddState by remember {
        mutableStateOf(data.favorite)
    }
//                        val infiniteTransaction = rememberInfiniteTransition()
//                        val colorTransaction by infiniteTransaction.animateColor(
//                            initialValue = favColor,
//                            targetValue = addFavColor,
//                            animationSpec = infiniteRepeatable(
//                                tween(durationMillis = 1000),
//                                repeatMode = RepeatMode.Reverse
//                            )
//                        )
    var colorState by remember {
        mutableStateOf(if (data.favorite) addFavColor else favColor)
    }
    val colorAnimState by animateColorAsState(
        targetValue = colorState,
        tween(durationMillis = 800)
    )

    Column(
        modifier = Modifier
            .padding(start = 8.dp)
            .width(180.dp)

    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier.background(Color.White),
                        horizontalArrangement = Arrangement.Start
                    ) {

                        if (data.offer > 0) {
                            Box(
                                modifier = Modifier
                                    .padding(
                                        vertical = 8.dp
                                    )
                                    .background(color = MaterialTheme.colorScheme.secondaryContainer)
                            ) {
                                Text(
                                    text = "${data.offer}% OFF",
                                    modifier = Modifier.padding(
                                        vertical = 4.dp,
                                        horizontal = 16.dp
                                    ),
                                    fontSize = 14.sp
                                )
                            }
                        }
                    }
                    Column(
                        modifier = Modifier.padding(
                            vertical = 8.dp,
                            horizontal = 16.dp
                        )
                    ) {
                        IconButton(
//                            imageVector = Icons.Outlined.Favorite,
                            modifier = Modifier
                                .size(32.dp)
                                .background(Color.White),
                            onClick = {
//                                favAddState = !favAddState
//                                colorState = if (favAddState) addFavColor else favColor
                                onAddFavoriteClick(data.id, false)
                            },
                            enabled = true,
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Favorite,
                                contentDescription = "contentDescription",
                                tint = Color.Red
                            )
                        }
                    }

                }




                Image(
                    modifier = Modifier
                        .size(100.dp)
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(vertical = 8.dp),
                    painter = rememberAsyncImagePainter(model = data.image),
                    contentDescription = "walletIcon",
                    contentScale = ContentScale.Crop,
                )
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.Start
                ) {

                    Text(
                        text = (if (data.actualPrice != data.offerPrice) data.actualPrice else ""),
                        modifier = Modifier.padding(start = 16.dp, top = 8.dp),
                        style = TextStyle(textDecoration = TextDecoration.LineThrough),
                        fontSize = 12.sp
                    )

                    Text(
                        text = data.offerPrice, modifier = Modifier.padding(start = 16.dp),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = data.name,
                        modifier = Modifier.padding(start = 16.dp),
                        maxLines = 2,
                    )
                }
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp, vertical = 8.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(text = "Add")

                }

            }


        }

    }
}