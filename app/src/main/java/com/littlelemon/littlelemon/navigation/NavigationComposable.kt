package com.littlelemon.littlelemon.navigation

import android.content.Context.MODE_PRIVATE
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.littlelemon.littlelemon.screens.Onboarding
import com.littlelemon.littlelemon.ui.home.Home
import com.littlelemon.littlelemon.screens.Profile

// Step 02-02-04: Create the Navigation Composable
@Composable
fun NavigationComposable(navController : NavHostController) {

    NavHost(navController = navController, startDestination = determineStartDestination()) {
        composable(Onboarding.route) {
            Onboarding(navController)
        }
        composable(Home.route) {
            /*
            version-01:
            Home()
            version-02:
            Home(navController)
             */
            Home(navController)
        }
        composable(Profile.route) {
            /*
            version-01:
            Profile()
            version-02:
            Profile(navController)
             */
            Profile(navController)
        }

    }
}

@Composable
fun determineStartDestination(): String {
    /*
    Determine the startDestination.
    If user data is stored in shared preferences, then the start destination is Onboarding;
    otherwise, the start destination is Home.
     */

    //val context = LittleLemonApplication.appContext
    val context = LocalContext.current
    val sharedPreferences by lazy {
        context.getSharedPreferences("LittleLemon", MODE_PRIVATE)
    }

    //val userRegisteredLiveData = MutableLiveData<Boolean>()
    val userRegistered:Boolean = sharedPreferences.getBoolean("Registered",false)

    if(userRegistered) {
        return Home.route
    }
    return Onboarding.route
}