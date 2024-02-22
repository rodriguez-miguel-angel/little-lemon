package com.littlelemon.littlelemon.ui.home

import com.littlelemon.littlelemon.data.MenuItemRoom

// Step 02-05-02: Add Menu breakdown
class FilterHelper {//TODO create a FilterHelperTest and write a unit test for filterMenuItems

    fun filterMenuItems(type: FilterType, menuItemsList: List<MenuItemRoom>): List<MenuItemRoom> {
        return when (type) {
            FilterType.All -> menuItemsList
            FilterType.Starters -> menuItemsList.filter { menuItemRoom -> menuItemRoom.category == "starters" } // TODO("only products with category equal to Food")
            FilterType.Mains -> menuItemsList.filter { menuItemRoom -> menuItemRoom.category == "mains" } // TODO("only products with category equal to Food")
            FilterType.Desserts -> menuItemsList.filter { menuItemRoom -> menuItemRoom.category == "desserts" } // TODO("only products with category equal to Dessert")
            FilterType.Drinks -> menuItemsList.filter { menuItemRoom -> menuItemRoom.category == "drinks" } // TODO("only products with category equal to Drinks")

        }
    }

}