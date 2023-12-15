package com.example.aroshatest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aroshatest.ui.theme.AroshaTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AroshaTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavGraph(SharedViewModel())
                }
            }
        }
    }
}
//page navigation
@Composable
fun NavGraph(sharedViewModel: SharedViewModel) {
    val navController = rememberNavController()
    val savedUserInfo = UserPreferences.getUser(LocalContext.current)

    NavHost(navController = navController, startDestination = if (savedUserInfo != null) "secondPage" else "firstPage") {
        composable("firstPage") {
            FirstPage(navController = navController, sharedViewModel = sharedViewModel)
        }
        composable("secondPage") {
            SecondPage(navController = navController, sharedViewModel = sharedViewModel)
        }
    }
}//end of navingation


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AroshaTestTheme {
    }
}