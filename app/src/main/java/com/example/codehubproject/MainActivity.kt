package com.example.codehubproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.codehubproject.navigation.AppNavigation
import com.example.codehubproject.ui.theme.CodeHubProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
//            CHBankApp(text = "John")
            CodeHubProjectTheme {
                AppNavigation()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CHBankMobAppPreview() {
    CodeHubProjectTheme {
//        LoginScreen()
    }
}