package com.example.testmodule

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor() : ViewModel() {

    private val count_ = mutableIntStateOf(0)
    val count: State<Int> = count_
    private val errorMessage_ = mutableStateOf("")
    val errorMessage: State<String> = errorMessage_

    val increment = {
        count_.value++
        errorMessage_.value = ""
    }
    val decrement = {
        if (count_.value <= 0)
            errorMessage_.value = "Count cannot be less than 0"
        else {
            count_.value--
            errorMessage_.value = ""
        }
    }

}