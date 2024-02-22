package com.littlelemon.littlelemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import com.littlelemon.littlelemon.data.AppDatabase
import com.littlelemon.littlelemon.data.MenuItemNetwork
import com.littlelemon.littlelemon.data.MenuItemRoom
import com.littlelemon.littlelemon.data.MenuNetwork
import com.littlelemon.littlelemon.navigation.NavigationComposable
import com.littlelemon.littlelemon.ui.home.MenuItems
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/*
MainActivity.kt – This file represents the screen UI and holds the entire application logic.
It also contains httpClient which will be used to retrieve data from the network and an instance of the local database used to store the retrieved data.
 */

class MainActivity : ComponentActivity() {

    /*
    Step 02-04-02: Fetch the menu. Retrieve the data
     */
    private val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))
        }
    }
    private suspend fun fetchMenu(): List<MenuItemNetwork> {
        // data URL: https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json
        val response: MenuNetwork = httpClient
            .get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")
            .body()
        return response.menu ?: listOf()
    }

    /*
    Step 02-04-03: Store data in a Room database. Store data in Room Database
    */
    private val database by lazy {
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database").build()
    }
    private fun saveMenuToDatabase(menuItemsNetwork: List<MenuItemNetwork>) {
        val menuItemsRoom = menuItemsNetwork.map { it.toMenuItemRoom() }
        database.menuItemDao().insertAll(*menuItemsRoom.toTypedArray())
    }

    private fun getMenuFromDatabase(): List<MenuItemRoom>? {
        return database.menuItemDao().getAll().value
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            /*
            version-01:
            LittleLemonTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                )
                    Greeting("Android")
                }
            }
             */
            MyNavigation()

            MyMenuItems()

        }

        // Step 02-04-03: Store data in a Room database. Store data in Room Database
        lifecycleScope.launch(Dispatchers.IO) {
            if (database.menuItemDao().isEmpty()) {
                val menuItems: List<MenuItemNetwork> = fetchMenu()
                saveMenuToDatabase(menuItems)

            }
        }

    }

    // Step 02-02-04: Create the Navigation Composable
    @Composable
    fun MyNavigation() {
        val navController = rememberNavController()
        NavigationComposable(navController)
    }


    // Step 02-04-05: Display menu items
    @Composable
    fun MyMenuItems() {
        val databaseMenuItems by database.menuItemDao().getAll().observeAsState(initial = emptyList())
        MenuItems(databaseMenuItems)

    }

}


/*
version-00:

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LittleLemonTheme {
        Greeting("Android")
    }
}

 */
