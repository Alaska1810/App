package com.example.app


import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.app.ui.theme.AppTheme
import com.example.app.ui.navigation.BottomNavGraph
import com.example.app.ui.screens.RecordatorioViewModel
import com.example.app.ui.screens.RecordatorioViewModelFactory

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    @ExperimentalMaterial3Api
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val owner =
                LocalViewModelStoreOwner.current
            val navHost = rememberNavController()
            AppTheme {
                owner?.let {
                    val viewModel: RecordatorioViewModel =
                        viewModel(
                            it,
                            "TaskViewModel",
                            RecordatorioViewModelFactory(
                                application = this.application)
                        )
                    BottomNavGraph(
                        navHost = navHost,
                        recordatorioViewModel = viewModel)
                }
            }
        }
    }
}

/*@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppTheme {
        Greeting("Android")
    }
}*/