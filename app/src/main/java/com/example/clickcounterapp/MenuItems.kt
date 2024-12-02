package com.example.clickcounterapp

import androidx.lifecycle.ViewModel

/***
 * Stores list of MenuItem objects using a ViewModel (enables more persistent storage)
 */
class MenuItems: ViewModel(){
    var myItems: List<MenuItem> = listOf(            // stores menuItem info i.e. name & price
        MenuItem("Cosmic Tonkotsu", "12.99"),
        MenuItem("Nebula Shoyu", "11.99"),
        MenuItem("Supernova Spicy Miso", "13.49"),
        MenuItem("Moon Rabbit Mochi", "5.99"),
        MenuItem("Solar Yuzu Lemonade", "3.99"),
    )
}