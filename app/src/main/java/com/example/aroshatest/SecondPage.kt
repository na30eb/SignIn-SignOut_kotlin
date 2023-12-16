package com.example.aroshatest
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    // data table
    val tableData = listOf(
        listOf("نام", "نام خانوادگی", "کدملی","تاریخ تولد"),
        listOf(userInfo?.firstName.orEmpty(), userInfo?.lastName.orEmpty(), userInfo?.id.orEmpty(), userInfo?.birthdate.orEmpty())
    )

    LazyColumn (
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Color.White)
            .padding(3.dp)){
        item { 
            Spacer(modifier = Modifier.height(50.dp))
        }
        items(1) {
            Image(
                painter = painterResource(id = R.drawable.pic3),
                contentDescription = null,
                Modifier.fillMaxWidth()
                    .padding(20.dp)
            )
        }
        item {
            Spacer(modifier = Modifier.height(50.dp))
        }
        // Create the header row
        item {
            Row(modifier = Modifier
                .padding(10.dp, 0.dp, 10.dp, 0.dp)
                .clip(MaterialTheme.shapes.medium)
            ) {
                tableData[0].forEach { header ->
                    Text(
                        text = header,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .weight(1f)
                            .background(Color(0xFF78D1FF))
                            .padding(2.dp, 5.dp, 10.dp, 10.dp)

                    )
                }
            }
        }

        // Create the data rows
        items(tableData.drop(1)) { row ->
            Row (modifier = Modifier
                .padding(10.dp, 2.dp, 10.dp, 0.dp)
                .clip(MaterialTheme.shapes.medium)
            ){
                row.forEach { cell ->
                    Text(
                        text = cell,
                        fontSize = 10.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .weight(1f)
                            .background(Color(0xFF78D1FF))
                            .padding(2.dp, 20.dp, 10.dp, 20.dp)
                    )
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(50.dp))
        }
        item {
            Button(onClick = {
                // Delete user information from SharedPreferences
                //UserPreferences.saveUser(context, UserInfo("", "", "", ""))
                UserPreferences.clearUser(context)
                // Navigate back to the first page
                navController.navigate("firstPage")
            }) {
                Text(text = "خروج")
            }

        }

    }//end of table

}//end of second pagde
