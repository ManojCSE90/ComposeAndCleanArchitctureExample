package com.example.testmodule

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.testmodule.ui.theme.ComposeandCleanArchitctureExampleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeandCleanArchitctureExampleTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = {
                                TopBarScreen()
                            },
                            colors = TopAppBarDefaults.topAppBarColors()
                        )

                    },
                    bottomBar = {
                        BottomAppBar {
                            BottomBarScreen()
                        }
                    }
                )
                { innerPadding ->
                    GreetingPreview(modifier = Modifier.padding(innerPadding))
                    //Counter(modifier = Modifier.padding(innerPadding))
                }
            }
        }

    }


}

@Composable
fun CountryCard(modifier: Modifier = Modifier) {

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .wrapContentHeight(align = Alignment.Top)
            .border(1.dp, color = Color.LightGray)
            .shadow(elevation = 2.dp)
    ) {


        Row(
            modifier = modifier
                .fillMaxWidth()
        ) {

            Column(
                modifier = modifier
                    .fillMaxWidth(0.2f)
                    .weight(0.2f, true)
            ) {
                Text(text = "India")
                Text(text = "India")
                Text(text = "India")

            }
            Column(
                modifier = modifier
                    .fillMaxWidth(0.8f)
                    .weight(0.8f, true)
            ) {
                Text(text = "India")
                Text(text = "India")
                Text(text = "India")

            }

        }
    }

}

@Composable
private fun Counter(modifier: Modifier, viewModel: TestViewModel = hiltViewModel()) {

    Log.d("TAG", "Counter: is called")

    if (viewModel.errorMessage.value.isNotEmpty()) {
        Toast.makeText(LocalContext.current, viewModel.errorMessage.value, Toast.LENGTH_SHORT)
            .show()
    }

    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = {
            viewModel.increment()
        }) {
            Text(text = "Increment")
        }

        Text(text = "Count is ${viewModel.count.value}", fontSize = 30.sp)

        Button(onClick = {
            viewModel.decrement()
        }) {
            Text(text = "Decrement")
        }
    }

}

@Composable
private fun BottomBarScreen() {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopStart) {
        Text(text = "Bottom Bar title here")
    }
}

@Composable
private fun TopBarScreen() {

    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopStart) {
        Text(text = "Top Bar title here")
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview(modifier: Modifier = Modifier) {
    ComposeandCleanArchitctureExampleTheme {
        CountryCard(modifier = modifier)
    }
}