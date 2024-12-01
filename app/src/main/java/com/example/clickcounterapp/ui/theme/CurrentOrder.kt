package com.example.clickcounterapp.ui.theme
import android.icu.util.Currency
import androidx.compose.runtime.mutableStateListOf  // used for a dynamic list
import androidx.lifecycle.ViewModel // used to store data components can share
import com.example.clickcounterapp.MenuItem

/**
 * Name: CurrentOrder
 * Goal: Stores current user's order info.
 * Note: Extends the viewModel class since that
 * class can preserve and share data across screens. */
class CurrentOrder : ViewModel(){
    val orderItems = mutableStateListOf<MenuItem>() // stores menuItem info i.e. name & price
    val numItems = mutableStateListOf<Int>()        //Parallel vector tracks number of each item
}

