package com.example.aroshatest

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
// passing data
class SharedViewModel : ViewModel() {
        var firstName: String by mutableStateOf("")
        var lastName: String by mutableStateOf("")
        var id: String by mutableStateOf("")
        var date: String by mutableStateOf("")

}


