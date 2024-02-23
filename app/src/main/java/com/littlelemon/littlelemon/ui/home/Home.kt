package com.littlelemon.littlelemon.ui.home


import android.util.Log
import android.view.Menu
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.littlelemon.littlelemon.R
import com.littlelemon.littlelemon.data.AppDatabase
import com.littlelemon.littlelemon.data.MenuItemNetwork
import com.littlelemon.littlelemon.data.MenuItemRoom
import com.littlelemon.littlelemon.ui.item.MenuItem
import com.littlelemon.littlelemon.ui.theme.LittleLemonColor
import com.littlelemon.littlelemon.ui.theme.LittleLemonType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// See <https://www.baeldung.com/kotlin/static-methods>
class MenuList {


    companion object {

        /*
        version-02:
         */
        var searchPhrase by mutableStateOf("")
            private set
        var category by mutableStateOf("")
            private set
        fun updateSearchPhrase(input: String) {
            searchPhrase = input
        }

        fun updateCategory(input: String) {
            category = input
        }

        private var menuList by mutableStateOf(emptyList<MenuItemRoom>())

        fun getList(): List<MenuItemRoom> {
            return menuList
        }

        fun initList(items: List<MenuItemRoom>) {
            menuList = items
        }
    }

}


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
            /*
            version-01:
            Text(
                text = "Home Screen",
                style = LittleLemonType.leadText,
                color = LittleLemonColor.black)
             */
            Hero()
            Menu()
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
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.10F))

    {
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
                .padding(start = 10.dp, top = 20.dp)
                .size(50.dp)
                .clickable {
                    navController.navigate("Profile")
                }

        )
    }


}

@Composable
fun Hero() {

    /*
    version-01:
    var searchPhrase by remember {
        mutableStateOf("")
    }
     */


    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(.50F)
        .background(LittleLemonColor.green)
        .padding(start = 10.dp, end = 10.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
        ) {
            Box(modifier = Modifier
                .align(Alignment.Start)) {
                Text(
                    text = stringResource(id = R.string.restaurant_name),
                    style = LittleLemonType.displayTitle,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Text(
                    text = stringResource(id = R.string.location),
                    style = LittleLemonType.subtitle,
                    color = LittleLemonColor.white,
                    modifier = Modifier
                        .fillMaxWidth(.5F)
                        .padding(top = 50.dp)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.60F),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = stringResource(id = R.string.description),
                    textAlign = TextAlign.Left,
                    style = LittleLemonType.paragraphText,
                    color = LittleLemonColor.white,
                    modifier = Modifier.fillMaxWidth(.50F)
                )
                Image(
                    painter = painterResource(id = R.drawable.hero_image),
                    contentDescription = "Hero Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(150.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .padding(start = 10.dp)
                )
            }
            // Step 02-05-01: Filter menu items
            TextField(
                value = MenuList.searchPhrase,
                onValueChange = { MenuList.updateSearchPhrase(it)},
                label = { Text(
                    text = "Enter search phrase") },
                maxLines = 1,
                singleLine = true,
                leadingIcon = {Icon(Icons.Filled.Search, contentDescription = "Search Icon")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
                    .background(
                        LittleLemonColor.white,
                        shape = RoundedCornerShape(20.dp)
                    )
            )


        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Menu() {

    var menuList: List<MenuItemRoom> = MenuList.getList()

    var items: List<MenuItemRoom> by remember {
        mutableStateOf(emptyList<MenuItemRoom>())
    }

    menuList = if(MenuList.searchPhrase.isNotEmpty()) {
        val filteredList = menuList.filter {
            it.title.contains(MenuList.searchPhrase,ignoreCase=true)
        }
        filteredList
    } else {
        MenuList.getList()
    }

    items = menuList


    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()) {
        Text(
            text = stringResource(id = R.string.order),
            style = LittleLemonType.sectionTitle,
            modifier = Modifier
                .fillMaxWidth()
        )


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.15F),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Top
        ) {
            OutlinedButton(
                onClick = {
                    MenuList.updateCategory("starters")
                },
                colors = ButtonDefaults.buttonColors(
                    LittleLemonColor.cloud
                ),
                shape = RoundedCornerShape(20),
                contentPadding = PaddingValues(10.dp)
            ) {
                Text(
                    text = "Starters",
                    textAlign = TextAlign.Center,
                    style = LittleLemonType.sectionCategories,
                    color = LittleLemonColor.black,
                )
            }
            OutlinedButton(
                onClick = {
                    MenuList.updateCategory("mains")
                },
                colors = ButtonDefaults.buttonColors(
                    LittleLemonColor.cloud
                ),
                shape = RoundedCornerShape(20),
                contentPadding = PaddingValues(10.dp)
            ) {
                Text(
                    text = "Mains",
                    textAlign = TextAlign.Center,
                    style = LittleLemonType.sectionCategories,
                    color = LittleLemonColor.black,
                )
            }
            OutlinedButton(
                onClick = {
                    MenuList.updateCategory("desserts")
                },
                colors = ButtonDefaults.buttonColors(
                    LittleLemonColor.cloud
                ),
                shape = RoundedCornerShape(20),
                contentPadding = PaddingValues(10.dp)
            ) {
                Text(
                    text = "Desserts",
                    textAlign = TextAlign.Center,
                    style = LittleLemonType.sectionCategories,
                    color = LittleLemonColor.black,
                )
            }
            OutlinedButton(
                onClick = {
                    MenuList.updateCategory("drinks")
                },
                colors = ButtonDefaults.buttonColors(
                    LittleLemonColor.cloud
                ),
                shape = RoundedCornerShape(20),
                contentPadding = PaddingValues(10.dp)
            ) {
                Text(
                    text = "Drinks",
                    textAlign = TextAlign.Center,
                    style = LittleLemonType.sectionCategories,
                    color = LittleLemonColor.black,
                )
            }
        }

        // via category
        menuList = if(MenuList.category.isNotEmpty()) {
            val filteredList = filterMenu(menuList)
            filteredList
        } else {
            MenuList.getList()
        }
        items = menuList

        Divider(
            thickness = 1.dp,
            color = LittleLemonColor.black)

        Box(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxHeight()

            ) {
                items(
                    items = items,
                    itemContent = { menuItem ->
                        MenuItem(menuItem = menuItem)
                    }
                )
            }

        }
    }
}

@Composable
fun filterMenu(items: List<MenuItemRoom>): List<MenuItemRoom> {
    val filteredList: List<MenuItemRoom> = items.filter {
        it.category.contains(MenuList.category,ignoreCase=true)
    }
    return filteredList
}

// Step 02-04-05: Display menu items
@Composable
fun MenuItems(items: List<MenuItemRoom>) {

    /*
    version-01:
     */
    MenuList.initList(items)
    /*
    version-02: TODO
    HomeViewModel().menuList = items
     */
}

