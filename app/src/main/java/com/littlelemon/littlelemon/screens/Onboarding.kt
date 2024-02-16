package com.littlelemon.littlelemon.screens

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.edit
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavHostController
import com.littlelemon.littlelemon.R
import com.littlelemon.littlelemon.ui.theme.LittleLemonColor
import com.littlelemon.littlelemon.ui.theme.LittleLemonType
import java.util.regex.Pattern


// Step 02-02-05: Update the onboarding navigation flow
@Composable
fun Onboarding(navController : NavHostController) {

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
            Header()
            Box(modifier = Modifier
                .fillMaxWidth()
                .background(LittleLemonColor.green)
                .padding(60.dp)) {
                Text(
                    text = "Let's get to know you",
                    textAlign = TextAlign.Center,
                    style = LittleLemonType.displayTitle,
                    fontSize = 36.sp,
                    color = LittleLemonColor.white,
                    modifier = Modifier.wrapContentWidth()

                )
            }
            PersonalInformation(navController)
        }

    }


}


/*
version-00:
@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    Onboarding()
}
 */




// Step 02-01-02: Add the Header
@Composable
fun Header() {
    Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = "Little Lemon Logo",
        modifier = Modifier
            .fillMaxWidth(0.60F)
            .size(100.dp)
    )

}

@Composable
fun PersonalInformation(navController:NavHostController) {
    // Step 02-01-04: Collect user input
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var emailAddress by remember { mutableStateOf("") }

    val context = LocalContext.current

    fun isFilled():Boolean {
        return !firstName.isBlank() && !lastName.isBlank() && !emailAddress.isBlank()
    }

    /*
    See<https://www.baeldung.com/kotlin/regular-expressions>.
    See<https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/matches.html>.
    See<https://stackoverflow.com/questions/72117435/kotlin-android-email-validationrod>.
    */

    fun validateEmail():Boolean {
        val EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
        )
        return EMAIL_ADDRESS_PATTERN.matcher(emailAddress).matches()
    }

    fun registerUser() {

        Log.d("PersonalInformation", "Register User[[Function Called]]")

        val sharedPreferences by lazy {
            context.getSharedPreferences("LittleLemon", Context.MODE_PRIVATE)
        }

        sharedPreferences.edit(commit = false) {
            putString("FirstName", firstName)
            putString("LastName", lastName)
            putString("Email", emailAddress)
            putBoolean("Registered", true)
        }

    }

    Column() {
        Text(
            text = "Personal Information",
            style = LittleLemonType.leadText,
            color = LittleLemonColor.black,
            modifier = Modifier.padding(start = 10.dp, top = 20.dp, bottom = 40.dp)
        )

        OutlinedTextField(value = firstName,
            onValueChange = { firstName = it },
            label = { Text(
                text = "First Name:") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
        OutlinedTextField(value = lastName,
            onValueChange = { lastName = it },
            label = { Text(
                text = "Last Name:") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
        OutlinedTextField(value = emailAddress,
            onValueChange = { emailAddress = it },
            label = { Text(
                text = "Email:") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
        // Step 02-01-05: Add a register button
        // Step 02-02-05: Update the onboarding navigation flow
        Button(
            onClick = {
                if(isFilled() && validateEmail()) {
                    Toast.makeText(context,"Registration successful!", Toast.LENGTH_SHORT).show()
                    registerUser()
                    navController.navigate("Home")
                }
                else {
                    Toast.makeText(context,"Registration unsuccessful. Please enter all data.", Toast.LENGTH_SHORT).show()
                }},
            colors = ButtonDefaults.buttonColors(
                LittleLemonColor.yellow
            ),
            shape = RoundedCornerShape(20),
            modifier = Modifier.padding(start = 10.dp, end = 20.dp, top = 100.dp, bottom = 10.dp)
        ) {
            Text(
                text = "Register",
                textAlign = TextAlign.Center,
                style = LittleLemonType.leadText,
                color = LittleLemonColor.black,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }


}

