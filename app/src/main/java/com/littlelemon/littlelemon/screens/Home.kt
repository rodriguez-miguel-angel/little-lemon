package com.littlelemon.littlelemon.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.littlelemon.littlelemon.R
import com.littlelemon.littlelemon.ui.theme.LittleLemonColor
import com.littlelemon.littlelemon.ui.theme.LittleLemonType

// Step 02-02-02: Create empty screens
// navController : NavHostController
@Composable
fun Home(navController:NavHostController) {
    Surface(
        modifier = Modifier
            .background(LittleLemonColor.white)
            .fillMaxSize()
    ) {
        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Header(navController)
            Text(
                text = "Home Screen",
                style = LittleLemonType.leadText,
                color = LittleLemonColor.black)
        }
    }
}

/*
version-00:
@Preview(showBackground = true)
@Composable
fun HomePreview() {
    Home()
}
 */

// Step 02-03-04: Navigate to the Profile screen
@Composable
fun Header(navController:NavHostController) {
    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Little Lemon Logo",
            modifier = Modifier
                .fillMaxWidth(0.60F)
                .size(100.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Profile Image",
            modifier = Modifier
                //.fillMaxWidth(0.30F)
                .padding(start = 10.dp, top = 25.dp)
                .size(50.dp).clickable {
                    navController.navigate("Profile")
                }

        )
    }


}