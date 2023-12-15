package com.example.aroshatest
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun SecondPage(navController: NavController, sharedViewModel: SharedViewModel) {
    val context = LocalContext.current
    //sharedpref
    var userInfo by remember {
        mutableStateOf<UserInfo?>(null)
    }
    // Retrieve user information from SharedPreferences when the Composable is first created
    DisposableEffect(Unit) {
        userInfo = UserPreferences.getUser(context)
        onDispose { }
    }
    // Sample data for the table
    val tableData = listOf(
        listOf("first name ", "last name", "id","birthday"),
        listOf(userInfo?.firstName.orEmpty(), userInfo?.lastName.orEmpty(), userInfo?.id.orEmpty(), userInfo?.birthdate.orEmpty())
    )

    LazyColumn {
        // Create the header row
        item {
            Row {
                tableData[0].forEach { header ->
                    Text(
                        text = header,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp)
                    )
                }
            }
        }

        // Create the data rows
        items(tableData.drop(1)) { row ->
            Row {
                row.forEach { cell ->
                    Text(
                        text = cell,
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp)
                    )
                }
            }
        }
    }//end of table

    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "first name: ${sharedViewModel.firstName} | Last name: ${sharedViewModel.lastName} | id: ${sharedViewModel.id} | date: ${sharedViewModel.date}")

        // Navigate back to the first page if needed
        Button(onClick = {
            // Delete user information from SharedPreferences
            UserPreferences.saveUser(context, UserInfo("", "", "", ""))

            // Navigate back to the first page
            navController.navigate("firstPage")
        }) {
            Text(text = "Exit")
        }

    }//end of col
}//end of second pagde
@Preview
@Composable
fun SecondPagePreview() {
    val navController = rememberNavController()
    val sharedViewModel = SharedViewModel()

    SecondPage(navController = navController, sharedViewModel = sharedViewModel)
}