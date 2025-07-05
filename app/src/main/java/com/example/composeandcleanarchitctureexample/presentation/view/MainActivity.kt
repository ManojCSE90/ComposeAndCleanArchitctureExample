package com.example.composeandcleanarchitctureexample.presentation.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.composeandcleanarchitctureexample.presentation.view.ui.theme.ComposeandCleanArchitctureExampleTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        setContent {
            ComposeandCleanArchitctureExampleTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar()
                    }
                ) { innerPadding ->
                    //modifier = Modifier.padding(innerPadding)
                    UserScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ConsumerDemo(modifier: Modifier = Modifier) {

    consumer()
    // Call the producer function to start emitting values
    // This will run in the Main thread, so it can update the UI
    // You can also use a CoroutineScope to launch the producer in a background thread
    // Here we are using a CoroutineScope to launch the producer
    // in the Main thread, so it can update the UI
    LaunchedEffect(Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            producer()
        }
    }

    Hello("Manoj", modifier = modifier)

}

fun consumer() {
    // This is a simple consumer that collects values from the producer
    CoroutineScope(Dispatchers.Main).launch {
        // Collect values from the producer flow
        producer()

            .collect { value ->
                // Do something with the collected value
                println("Received value: $value, Thread: ${Thread.currentThread().name}")
            }
    }
    /*CoroutineScope(Dispatchers.Main).launch {
        // Collect values from the producer flow
        delay(2000) // Delay to simulate a different timing
        producer()

            .collect { value ->
            // Do something with the collected value
            println("Received2 value: $value, Thread: ${Thread.currentThread().name}")
        }
    }*/
}

fun producer(): Flow<Int> {
    val sharedFlow = MutableSharedFlow<Int>()

    CoroutineScope(Dispatchers.Main).launch {

        for (i in 1..5) {
            println("Emitting value: $i, Thread: ${Thread.currentThread().name}")
            // Print the thread name to see where this is running
            sharedFlow.emit(i) // Emit the next value
            delay(1000) // Simulate some work

        }
    }

    // Return a flow that emits the values from the shared flow
    return sharedFlow
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBar() {
    val context = LocalContext.current
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
        title = {
            Text(text = "Top Bar title here")
        },
        actions = {


            IconButton(onClick = {
                Toast.makeText(context, "Edit Clicked", Toast.LENGTH_SHORT).show()
            }) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
            }

            IconButton(onClick = {
                Toast.makeText(context, "Call Clicked", Toast.LENGTH_SHORT).show()
            }) {
                Icon(imageVector = Icons.Default.Call, contentDescription = "Call")
            }

            IconButton(onClick = {
                Toast.makeText(context, "Add Clicked", Toast.LENGTH_SHORT).show()
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }

            IconButton(onClick = {

                Toast.makeText(context, "Menu Clicked", Toast.LENGTH_SHORT).show()
            }) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
            }
            /* IconButton(onClick = {
                 Toast.makeText(context, "AccountBox Clicked", Toast.LENGTH_SHORT).show()
             }) {
                 Icon(imageVector = Icons.Default.AccountBox, contentDescription = "AccountBox")
             }

             IconButton(onClick = {
                 Toast.makeText(context, "Check Clicked", Toast.LENGTH_SHORT).show()
             }) {
                 Icon(imageVector = Icons.Default.Check, contentDescription = "Check")
             }*/

        }
    )
}


@Composable
fun Hello(name: String, modifier: Modifier) {

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Hello $name!", modifier = modifier)

    }
}
