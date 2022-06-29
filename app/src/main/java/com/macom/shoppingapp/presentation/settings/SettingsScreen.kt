package com.macom.shoppingapp.presentation.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.macom.shoppingapp.presentation.settings.data.SettingsItem


@Composable
fun SettingsScreen() {
    val scrollState = rememberScrollState(0)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding(),

        ) {

        Text(
            text = "Settings",
            Modifier
                .padding(start = 24.dp),
            textAlign = TextAlign.Start,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState),
        ) {
            ShowProfileInfo(Modifier)
            Settings(modifier = Modifier)


        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ShowProfileInfo(modifier: Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(

            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
            elevation = CardDefaults.cardElevation(0.dp)

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()

                    .background(color = MaterialTheme.colorScheme.surface)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = "https://www.gravatar.com/avatar/2c7d99fe281ecd3bcd65ab915bac6dd5?s=250"),
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .size(120.dp)
                        .align(Alignment.CenterHorizontally)
                        .clip(CircleShape)

                )
                Text(
                    text = "Kevin Dukkon", modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 8.dp, bottom = 8.dp),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.displaySmall
                )
                Text(
                    text = "Head of Design @fintory", modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    fontSize = 14.sp
                )
                Text(
                    text = "I love helping designers find their voice", modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    fontSize = 14.sp
                )
                Text(
                    text = "www.finitory.com", modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    fontSize = 14.sp
                )
            }

        }

    }
}

@Composable
private fun Settings(modifier: Modifier) {


    val scrollState = rememberScrollState(0)
    Column(
        modifier = modifier
            .fillMaxWidth()

            .padding(top = 24.dp, start = 16.dp, end = 16.dp, bottom = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        val settingsItems: List<SettingsItem> =
            listOf(
                SettingsItem(
                    image = Icons.Outlined.Notifications,
                    name = "Notifications"
                ),
                SettingsItem(
                    image = Icons.Outlined.PrivacyTip,
                    name = "Privacy Policy"
                ),

                SettingsItem(
                    image = Icons.Outlined.Description,
                    name = "Communication Guidelines"
                ),
                SettingsItem(
                    image = Icons.Outlined.Category,
                    name = "Terms of service"
                ),
                SettingsItem(
                    image = Icons.Outlined.Block,
                    name = "Blocked Account"
                ),
                SettingsItem(
                    image = Icons.Outlined.Help,
                    name = "Support"
                ),

                )

        settingsItems.forEach { data ->
            SettingsOptionListItem(settingsItem = data)

        }
        var text by remember { mutableStateOf("text") }

        OutlinedTextField(
            value = text,
            onValueChange = {
                text = it
            },
            label = { Text("Label") }
        )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SettingsOptionListItem(settingsItem: SettingsItem) {
    Column(modifier = Modifier.fillMaxWidth()) {


        Card(

            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)

        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(MaterialTheme.colorScheme.inverseOnSurface),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = settingsItem.image,
                        modifier = Modifier.size(24.dp),
                        contentDescription = ""
                    )


                    Text(
                        text = settingsItem.name,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(start = 16.dp)
                    )

                }
                Column(modifier = Modifier.padding(end = 16.dp)) {
                    Icon(
                        imageVector = Icons.Default.ArrowForwardIos,
                        modifier = Modifier.size(16.dp),
                        contentDescription = ""
                    )
                }
            }

        }

    }
}

@Preview
@Composable
fun SettingsPreview() {
    SettingsScreen()
}

