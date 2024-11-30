package com.example.clickcounterapp
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.items

// Importing colors:
import com.example.clickcounterapp.ui.theme.*

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
            // NavHost defines the navigation graph
            NavHost(
                navController = navController,
                startDestination = "welcome",  // Set Welcome Page as the start
                modifier = Modifier.padding(innerPadding)  // Prevents overlap with bars
            ) {
                composable("welcome") { WelcomeScreen(navController) }
                composable("menu") { MenuScreen(navController) }
                composable("item_details") { MenuItemDetailsScreen(navController) }
                //composable("details/${clickedItem.title}") {DetailsScreen(navController)}
                //composable("order") { OrderScreen(navController) }
            }
        }
    )
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
            .background(color = Background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

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
fun MenuScreen(navController: NavController = rememberNavController()) {

    // Defines list of sample menu items with titles
    val sampleMenuItems = listOf(
        MenuItem("Cosmic Tonkotsu"),
        MenuItem("Sushi"),
        MenuItem("Tempura"),
        MenuItem("Udon"),
        MenuItem("Takoyaki")
    )

    // Defines a container for menu list
    Box(modifier = Modifier.background(Background)){
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            items(sampleMenuItems) { item ->
                MenuItemCard(item = item, onClick = {navController.navigate("item_details")})
            }
        }
    }
}

@Preview
@Composable
// Used to preview UI.
fun MenuScreenPreview(){
    MenuScreen()
}

/**
 * Defines a single menu item card.
 * Each card has details and gets passed a clickable activity.
 * @param: item, a MenuItem (includes data for each item)
 * @param: onclick: A function with no parameters and returns no values
 *      (the () indicates no parameters, and the -> Unit indicates it takes no values.)
 * */
@Composable
fun MenuItemCard(item: MenuItem, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp)  // Padding for each item
    ) {
        Text(
            text = item.title,
            style = MaterialTheme.typography.titleLarge  // Styling for the item name
        )
    }
}

/***
 * Lists given list of MenuItems.
 */
@Composable
fun MenuList(menuItems: List<MenuItem>) {

}

@Composable
fun MenuItemDetailsScreen(navController: NavController = rememberNavController()){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text("Hello, welcome to the details for your item.")
    }
}

@Preview
@Composable
fun MenuItemDetailsScreenPreview(){
    MenuItemDetailsScreen()
}