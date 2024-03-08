package dev.skrilltrax.fetch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import dev.skrilltrax.fetch.ui.home.HomeScreen
import dev.skrilltrax.fetch.ui.home.HomeScreenViewModel
import dev.skrilltrax.fetch.ui.theme.FetchTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: HomeScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FetchTheme(dynamicColor = false) {
                // A surface container using the 'background' color from the theme

                val uiState by viewModel.uiState.collectAsState()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen(uiState = uiState, refreshData = { viewModel.displayItems() })
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FetchTheme {
        Greeting("Android")
    }
}