package com.example.aroshatest

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
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
            Text(text = "تاریخ تولد") }
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

    //form validation function
    fun isFormValid(firstName: String, lastName: String, id: String, date: String): Boolean {
        return firstName.isNotBlank() && lastName.isNotBlank() && id.length == 10 && date.isNotBlank()
    }


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
            .background(Color.White)
            .padding(20.dp)
    ) {
        //top image
        Image(
            painter = painterResource(id = R.drawable.pic4),
            contentDescription = null,
            modifier = Modifier.padding(0.dp,0.dp,0.dp,40.dp)
        )
        //text inputs
        TextField(
            value = nameR,
            placeholder = {
                Text("مثال : نسترن")
            },
            onValueChange = {
                nameR = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            label = { Text("نام : ") },
        )

        TextField(
            value = familyR,
            placeholder = {
                Text("مثال : ابراهیمی")
            },
            onValueChange = {
                familyR = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            label = { Text("نام خانوادگی : ") },

        )
        TextField(
            value = idR,
            placeholder = {
                Text("مثال : 0123456789")
            },
            onValueChange = {
                    newId ->
                if (newId.length <= 10) {
                    idR = newId
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            label = { Text("کد ملی (10رقم) :")  },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            visualTransformation = VisualTransformation.None,
        )
        //date input
        datepicker(context = LocalContext.current, date = date)
        //sign in btn
        Button(onClick = {
            sharedViewModel.firstName = nameR
            sharedViewModel.lastName = familyR
            sharedViewModel.id = idR
            sharedViewModel.date = date.value
            val userInfo = UserInfo(nameR, familyR, idR, date.value)
            UserPreferences.saveUser(context, userInfo)
            //UserPreferences.saveUser(context =context, sharedViewModel = SharedViewModel())
            navController.navigate("secondPage")
        },enabled = isFormValid(nameR, familyR, idR, date.value),

        )
        {
            Text(text = "ثبت نام")
        }
    }
}