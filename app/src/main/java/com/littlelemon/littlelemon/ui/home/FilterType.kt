package com.littlelemon.littlelemon.ui.home

// Step 02-05-02: Add Menu breakdown
sealed class FilterType {
    object All : FilterType()
    object Starters : FilterType()
    object Mains : FilterType()
    object Desserts : FilterType()
    object Drinks : FilterType()
}