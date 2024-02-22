package com.littlelemon.littlelemon.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/*
Step 02-04-02: Fetch the menu
Network.kt– This file contains network data models.
JSON data will be converted to these models.

The instance of the MenuNetwork class represents the entire menu.
To retrieve individual menu items use the menu property of the MenuNetwork.
Each item has id, title description, price and image attributes.
 */

@Serializable
data class MenuNetwork(
    val menu: List<MenuItemNetwork>
)

@Serializable
data class MenuItemNetwork(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("price")
    val price: String,
    @SerialName("image")
    val image: String,
    val category: String,
) {
    // step-02-03: Store data in a Room database
    fun toMenuItemRoom() = MenuItemRoom(
        id = id, title = title, description = description, price = price.toDouble(), image = image, category = category
    )
}