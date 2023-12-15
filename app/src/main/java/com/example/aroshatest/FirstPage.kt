package com.example.aroshatest

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.util.Calendar
import java.util.Date

//datepicker function
@Composable
fun datepicker(context: Context, date: MutableState<String>){
    val year : Int
    val month:Int
    val day: Int
    val calender = Calendar.getInstance()
    year=calender.get(Calendar.YEAR)
    month=calender.get(Calendar.MONTH)
    day=calender.get(Calendar.DAY_OF_MONTH)
    calender.time= Date()

    val datePickerDialog= DatePickerDialog(
        context,{ _: DatePicker, year:Int, month:Int, dayOfMonth:Int ->
            date.value="$dayOfMonth/$month/$year"
        },year,month,day
    )
    Row {
        Button(
            onClick = {
                datePickerDialog.show()
            }, ) {
            Text(text = "Select your birthday ") }
        Text(text = "${date.value}", fontSize = 15.sp, textAlign = TextAlign.End)

    }
}
//first page show
@Composable
fun FirstPage(navController: NavController, sharedViewModel: SharedViewModel) {
    val context = LocalContext.current

    var nameR by rememberSaveable {
        mutableStateOf("")
    }
    var familyR by rememberSaveable {
        mutableStateOf("")
    }
    var idR by rememberSaveable {
        mutableStateOf("")
    }
    var date = rememberSaveable { mutableStateOf("") }
  //  val context = LocalContext.current




    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        datepicker(context = LocalContext.current, date = date)
        TextField(
            value = nameR,
            onValueChange = {
                // This block is executed whenever the value changes
                nameR = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            label = { Text("Name : ") }, // Optional: You can add a label for the text field
        )

        TextField(
            value = familyR,
            onValueChange = {
                // This block is executed whenever the value changes
                familyR = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            label = { Text("Family : ") }, // Optional: You can add a label for the text field
        )
        TextField(
            value = idR,
            onValueChange = {
                // This block is executed whenever the value changes
                idR = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            label = { Text("ID : ") }, // Optional: You can add a label for the text field
        )
        Text(text = date.value)


        Button(onClick = {
            sharedViewModel.firstName = nameR
            sharedViewModel.lastName = familyR
            sharedViewModel.id = idR
            sharedViewModel.date = date.value
            val userInfo = UserInfo(nameR, familyR, idR, date.value)
            UserPreferences.saveUser(context, userInfo)
            //UserPreferences.saveUser(context =context, sharedViewModel = SharedViewModel())
            navController.navigate("secondPage")
        }) {
            Text(text = "Sign in")
            // Your button content
        }


    }
}