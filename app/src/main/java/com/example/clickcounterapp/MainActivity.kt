package com.example.clickcounterapp

// Dependencies to set up layout:
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

// dependencies to set up navigation graph:
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.*
import androidx.compose.ui.graphics.SolidColor

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

// Importing colors:
import com.example.clickcounterapp.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Navigation()
            }
        }
    }
}

// Setting up Navigation:
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "welcome") {
        composable("welcome") { WelcomeScreen(navController) }
        composable("menu") { MenuScreen(navController) }
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
        Text(  // Text component
            text = "Welcome Page",
            style = MaterialTheme.typography.headlineLarge,
            color = Text
        )
        //Bottom Bar:
        Row(
            modifier = Modifier
                .height(56.dp)
                .width(200.dp)
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
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
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
                    onClick = { navController.navigate("order") },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Secondary,
                        contentColor = Text
                    )
                )
                {
                    Text(
                        text = "Order",
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentHeight(Alignment.CenterVertically),
                        //textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                }
        }
    }
}

@Preview
@Composable
fun WelcomeScreenPreview(){
    WelcomeScreen()
}


// This defines the components on the menu page, a column to store everything,
// and text saying where we are.
@Composable
fun MenuScreen(navController: NavController) {
    //val itemList = List(10) {"Item #$it"}   // Defining lists of items to show
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Menu Page",
            style = MaterialTheme.typography.headlineLarge
        )

        LazyColumn {
            items(10) {index ->
                Text("Item #$index",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(16.dp)
                )
                Button(onClick = { navController.navigate("welcome")}){
                    Text("Go to Welcome Page")
                }
            }

        }


    }
}