package com.example.app.ui.screens

/*import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.app.R
import com.example.app.ui.navigation.BottomBar
import com.example.app.ui.navigation.Screens


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun InicioScreen(navHost: NavHostController) {

    val navigation_item =

        Scaffold(
            bottomBar = {
                BottomBar(navHost = navHost)
            }
        ) {

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(fraction = 0.30f),
                    Alignment.TopEnd,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.inicio), contentDescription = "",
                        modifier = Modifier.fillMaxSize(), contentScale = ContentScale.FillBounds
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
            ) {
                Spacer(modifier = Modifier.height(260.dp))
                Text(
                    text = "Bienvenido/a", fontSize = 35.sp,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Recordatorio personal", fontSize = 20.sp,
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(25.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(shape = MaterialTheme.shapes.small)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Medicamentos",
                        modifier = Modifier
                            .padding(40.dp)
                            .fillMaxWidth()
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "   Mantente ocupado cuidando tu salud.", fontSize = 17.sp,
                    style = MaterialTheme.typography.displayMedium
                )

                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    Arrangement.Center, verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(onClick = {
                        navHost.navigate(Screens.Login.route)
                    }) {
                        Text(text = "Cerrar sesión")
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

            }
        }
}*/