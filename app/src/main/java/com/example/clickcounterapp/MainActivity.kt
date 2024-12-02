package com.example.clickcounterapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.clickcounterapp.ui.theme.Background
import com.example.clickcounterapp.ui.theme.CurrentOrder
import com.example.clickcounterapp.ui.theme.Primary
import com.example.clickcounterapp.ui.theme.Secondary
import com.example.clickcounterapp.ui.theme.Text

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                MainApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
/**
 * MainApp:
 * Goal: This function sets up navigation, and uses a scaffold to set up a top
 * bar and a bottom bar. Each screen/page is displayed within the content section.
 * */
fun MainApp(){
    val navController = rememberNavController()  // Initialize the NavController

    Scaffold(
        topBar = {  //
            TopAppBar(
                title = { Text("Luna Noodles") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Primary,
                    titleContentColor = Secondary)
            )
        },
        bottomBar = {
            BottomAppBar (
                containerColor = Primary,
            )
            {
                BottomNavButton(navController)
            }
        },
        content = { innerPadding ->
            // Setting up list of menu items:
            val currentMenu = MenuItems()
            // Setting up list of current orders:
            val currentOrder = CurrentOrder()

            // NavHost defines the navigation graph
            NavHost(
                navController = navController,
                startDestination = "welcome",  // Set Welcome Page as the start
                modifier = Modifier.padding(innerPadding)  // Prevents overlap with bars
            ) {
                composable("welcome") { WelcomeScreen(navController) }
                composable("menu") { MenuScreen(navController, currentMenu, currentOrder) }
                composable("item_details") { MenuItemDetailsScreen(navController, menuItem = MenuItem("Error", "0.00")) }
                //composable("details/${clickedItem.title}") {DetailsScreen(navController)}
                composable("order") { MyOrderScreen(navController, currentOrder) }
                composable("confirm_screen") { ConfirmationScreen() }
            }
        }
    )
}

// Defining each screen:
@Composable
// WelcomeScreen intakes a navController object
// (used to navigate between page since it remembers where they all are.)
fun WelcomeScreen(navController: NavController = rememberNavController()) {
    // We place everything in the page within a column, we center and fill this column so
    // it all looks nice and neat

    // Bottom Bar
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Primary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        val image = painterResource(R.drawable.menupicture)
        Image(
            modifier = Modifier
                .fillMaxSize(),
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}

//@Preview
@Composable
fun WelcomeScreenPreview(){
    WelcomeScreen()
}

/**
 * MenuScreen: defines a menu with scrolling items.
 * Clicking each item brings the user to that items details page.
 * */
@Composable
fun MenuScreen(navController: NavController = rememberNavController(), currentMenu: MenuItems, currentOrder: CurrentOrder) {
    Box(modifier = Modifier.background(Background)){
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            items(currentMenu.myItems) { item ->
                MenuItemCard(item = item, "Add",onClick = {currentOrder.orderItems.add(item)})
                HorizontalDivider(thickness = Dp.Hairline, color = Color.Black)
            }
        }
    }
}

@Preview
@Composable
// Used to preview UI.
fun MenuScreenPreview(){
    val currentMenu = MenuItems()
    val currentOrder = CurrentOrder()
    MenuScreen(rememberNavController(), currentMenu, currentOrder)
}

/**
 * Defines a single menu item card.
 * Each card has details and gets passed a clickable activity.
 * @param: item, a MenuItem (includes data for each item)
 * @param: onclick: A function with no parameters and returns no values
 *      (the () indicates no parameters for the onClick function,
 *      and the -> Unit indicates it returns no values.)
 * */
@Composable
fun MenuItemCard(item: MenuItem, buttonTitle: String,onClick: () -> Unit) {
    Column(
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                //.clickable(onClick = onClick)
                .padding(16.dp)  // Padding for each item
        ) {
            Text(
                text = item.title,
                style = MaterialTheme.typography.titleLarge
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "$${item.price}",
                style = MaterialTheme.typography.titleSmall,
            )
            Spacer(Modifier.width(20.dp))

        }
        Button(
            onClick = onClick
        ) {
            Text(text = buttonTitle)
        }
        Spacer(Modifier.width(20.dp))
        //HorizontalDivider(Dp.Hairline)
    }
}

@Composable
fun MenuItemDetailsScreen(navController: NavController = rememberNavController(), menuItem: MenuItem){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text("Hello, welcome to the details for your item:")
        Text(menuItem.title)
    }
}

/*
@Preview
@Composable
fun MenuItemDetailsScreenPreview(){
    MenuItemDetailsScreen(rememberNavController(), menuItem = MenuItem("Chicken Katsu", "11.99"))
}
*/
@Composable
fun MyOrderScreen(navController: NavController = rememberNavController(), currentOrder: CurrentOrder){
    Column(
        modifier = Modifier
            .background(Background)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ){
        var total = currentOrder.currentTotalAsString()
        Text(
            text = "Total: $$total",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp)

        )
        HorizontalDivider(thickness = 2.dp, color = Color.Black)
        Text(
            text = "Current Items: ",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp)

        )
        HorizontalDivider(thickness = Dp.Hairline, color = Color.Black)
        LazyColumn(
            modifier = Modifier
                //.fillMaxWidth()
                .weight(1f)
                .padding(8.dp)
        ) {
            items(currentOrder.orderItems) { item ->
                MenuItemCard(item = item, "Remove", onClick = {currentOrder.orderItems.remove(item)})
                HorizontalDivider(thickness = Dp.Hairline, color = Color.Black)
            }
        }
        Button(
            onClick = {sendOrder(navController, currentOrder)}
        ){
            Text("Pay Now")
        }
    }
}

fun sendOrder(navController: NavController, currentOrder: CurrentOrder){
    navController.navigate("confirm_screen")
    currentOrder.orderItems.clear()
}

@Preview
@Composable
fun MyOrderScreenPreview(){
    // Setting up list of menu items:
    val currentMenu = MenuItems()
    // Setting up list of current orders:
    val currentOrder = CurrentOrder()
    val testItem = MenuItem("Beef Stew", "10.99")
    currentOrder.orderItems.add(testItem)
    MyOrderScreen(rememberNavController(), currentOrder)
}

@Composable
fun ConfirmationScreen(){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Background)
    ){
        Text("Stellar!")
        Text("We hope you enjoy!")
    }
}

@Composable
fun BottomNavButton(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Primary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        //Bottom Bar:
        Row(
            modifier = Modifier
                .height(70.dp)
                .width(250.dp)
                .clip(RoundedCornerShape(28.dp))  // Oval shape
                .border(2.dp, SolidColor(Text), RoundedCornerShape(28.dp))
                .background(color = Secondary)
            //.align(Alignment.BottomCenter)
        ){
            Button(
                modifier = Modifier.weight(1f),
                onClick = { navController.navigate("menu") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Secondary,
                    contentColor = Text
                )
            )
            {
                Text(
                    text = "Menu",
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentHeight(Alignment.CenterVertically),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            VerticalDivider(
                color = Text,
                modifier = Modifier
                    .width(2.dp)
                    .fillMaxHeight()
            )

            Button(
                modifier = Modifier.weight(1f),
                onClick = {navController.navigate("order") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Secondary,
                    contentColor = Text
                )
            )
            {
                Text(
                    text = "My Order",
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentHeight(Alignment.CenterVertically),
                    style = MaterialTheme.typography.bodyLarge
                    //textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
            }
        }
    }
}