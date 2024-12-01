package com.example.clickcounterapp

import androidx.lifecycle.ViewModel

/***
 * Stores list of MenuItem objects using a ViewModel (enables more persistent storage)
 */
class MenuItems: ViewModel(){
    var myItems: List<MenuItem> = listOf(            // stores menuItem info i.e. name & price
        MenuItem("Cosmic Tonkotsu", "10.99"),
        MenuItem("Sushi", "12.50"),
        MenuItem("Tempura", "11.99"),
        MenuItem("Udon", "12.75"),
        MenuItem("Takoyaki", "6.75")
    )
}