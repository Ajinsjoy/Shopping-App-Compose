package com.macom.shoppingapp.presentation.homeScreen.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.QrCodeScanner
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolBar(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Card(
            elevation = CardDefaults.cardElevation(2.dp),
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 16.dp)

        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    modifier = Modifier
                        .padding(start = 8.dp),

                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon"
                )
                Text(text = "Search", modifier = Modifier.padding(start = 8.dp))
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Icon(
                        imageVector = Icons.Outlined.QrCodeScanner,
                        contentDescription = "",
                        modifier = Modifier.padding(end = 16.dp)
                    )

                }

            }


        }


    }

}

@Preview
@Composable
fun PreviewToolbar() {
    ToolBar(Modifier)
}