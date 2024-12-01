package com.example.clickcounterapp

import androidx.lifecycle.ViewModel

/***
 * MenuItem:
 * Goal: Holds info for each individual menu item.
 * */
class MenuItem(
    val title: String,  // Name of menu item i.e. "Beef Stew"
    val price: String,      // Price for 1 unit of item i.e. "10.99"
    /*val thumbnail
    val bigPicture
    val calories: String,
    val description: String,
    val allergens: String
    */
){

}

class MenuItems: ViewModel(){
    var items: List<MenuItem> = listOf(            // stores menuItem info i.e. name & price
        MenuItem("Cosmic Tonkotsu", "10.99"),
        MenuItem("Sushi", "12.50"),
        MenuItem("Tempura", "11.99"),
        MenuItem("Udon", "12.75"),
        MenuItem("Takoyaki", "6.75")
    )
}
