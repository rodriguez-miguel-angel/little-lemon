package com.littlelemon.littlelemon.navigation

// Step 02-02-03: Create the destinations
interface Destinations {
    val route:String
}

object Onboarding:Destinations {
    override val route = "Onboarding"
}

object Home:Destinations {
    override val route = "Home"
}

object Profile:Destinations {
    override val route = "Profile"
}