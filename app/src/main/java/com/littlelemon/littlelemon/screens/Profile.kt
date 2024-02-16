package com.littlelemon.littlelemon.screens

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.edit
import androidx.navigation.NavHostController
import com.littlelemon.littlelemon.R
import com.littlelemon.littlelemon.ui.theme.LittleLemonColor
import com.littlelemon.littlelemon.ui.theme.LittleLemonType

// Step 02-02-02: Create empty
@Composable
fun Profile(navController : NavHostController) {
    Surface(
        modifier = Modifier
            .background(LittleLemonColor.white)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // Step 02-03-01: Add the Header
            Header()
            // Step 02-03-02: Display user data
            DisplayProfileInformation(navController)
        }

    }
}

/*
version-00:
@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    Profile()
}
 */



@Composable
fun DisplayProfileInformation(navController:NavHostController) {

    val context = LocalContext.current

    val sharedPreferences by lazy {
        context.getSharedPreferences("LittleLemon", Context.MODE_PRIVATE)
    }

    val firstName: String? = sharedPreferences.getString("FirstName","")
    val lastName: String? = sharedPreferences.getString("LastName","")
    val email: String? = sharedPreferences.getString("Email","")


    fun clearUserPreferences() {

        Log.d("Profile", "Clear User Preferences[[Function Called]]")

        val editor = sharedPreferences.edit()
        editor.clear().apply()

    }


    Column() {
        Text(
            text = "Profile Information:",
            style = LittleLemonType.leadText,
            color = LittleLemonColor.black,
            modifier = Modifier.padding(start = 10.dp, top = 20.dp, bottom = 40.dp)
        )
        if (firstName != null) {
            OutlinedTextField(
                value = firstName,
                onValueChange = { },
                enabled = false,
                label = {
                    Text(
                        text = "First Name:"
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )
        }
        if (lastName != null) {
            OutlinedTextField(
                value = lastName,
                onValueChange = { },
                enabled = false,
                label = {
                    Text(
                        text = "Last Name:"
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )
        }
        if (email != null) {
            OutlinedTextField(
                value = email,
                onValueChange = { },
                enabled = false,
                label = {
                    Text(
                        text = "Email:"
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )
        }
        // Step 02-03-03: Log out button
        Button(
            onClick = {
                clearUserPreferences()
                navController.navigate("Onboarding")
                },
            colors = ButtonDefaults.buttonColors(
                LittleLemonColor.yellow
            ),
            shape = RoundedCornerShape(20),
            modifier = Modifier.padding(start = 10.dp, end = 20.dp, top = 100.dp, bottom = 10.dp)
        ) {
            Text(
                text = "Log out",
                textAlign = TextAlign.Center,
                style = LittleLemonType.leadText,
                color = LittleLemonColor.black,
                modifier = Modifier.fillMaxWidth()
            )
        }

    }
}