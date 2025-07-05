package com.example.composeandcleanarchitctureexample.presentation.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composeandcleanarchitctureexample.presentation.viewmodel.UserViewModel

@Composable
fun UserScreen(modifier: Modifier = Modifier, viewModel: UserViewModel = hiltViewModel()) {
    val users = viewModel.users.value

    LazyColumn(modifier = modifier) {
        items(users) { user ->
            Text(text = "${user.name} - ${user.email}", modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 4.dp))
        }
    }
}
