package com.macom.shoppingapp.presentation.homeScreen.component

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.macom.shoppingapp.R
import com.macom.shoppingapp.domian.model.Product
import com.macom.shoppingapp.presentation.homeScreen.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Product(
    modifier: Modifier,
    product: List<Product>,
    favorite: Boolean,
    scope: CoroutineScope,
    modalBottomSheetState: ModalBottomSheetState,
    bottomSheetState: MutableState<Int>
) {
    val context = LocalContext.current
    val viewModel: HomeViewModel = hiltViewModel()
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {

        product.let { product ->

            LazyRow {
                items(product) { data ->

                    ProductItem(
                        data,
                        context,
                        onAddFavoriteClick = { id, status ->
                            viewModel.addFavorite(id, status)
                        },
                        onItemClick = {

                            scope.launch {
                                bottomSheetState.value = 0
                                modalBottomSheetState.show()
                            }
                        })
                }
            }

        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductItem(
    data: Product,
    context: Context,
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
                                favAddState = !favAddState
                                colorState = if (favAddState) addFavColor else favColor
                                onAddFavoriteClick(data.id, favAddState)

                            },
                            enabled = true,
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Favorite,
                                contentDescription = "contentDescription",
                                tint = colorAnimState
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
                    onClick = { onItemClick() },
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

