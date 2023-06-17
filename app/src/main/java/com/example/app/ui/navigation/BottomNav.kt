package com.example.app.ui.navigation

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.app.R
import com.example.app.ui.screens.RecordatorioViewModel


@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNav(navHost: NavHostController,
              viewModel: RecordatorioViewModel
){

   Scaffold (
        bottomBar = {
            BottomBar(navHost = navHost)
        }
           ){
        //BottomNavGraph(navHost = navHost, viewModel)
        InicioScreen(navHost = navHost)
        //navHost.navigate(Screens.InicioScreen.route
    }

}
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
}

@Composable
fun BottomBar(navHost: NavHostController){
    val screens = listOf(
        BottomBarScreen.Inicio,
        BottomBarScreen.Nuevo,
        BottomBarScreen.Milista
    )
    val navStackBackEntry by navHost.currentBackStackEntryAsState()
    val currentDestination = navStackBackEntry?.destination

    Row(modifier = Modifier
        .padding(start = 1.dp, end = 1.dp, top = 0.dp, bottom = 0.dp)
        .background(MaterialTheme.colorScheme.onPrimary)
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
        ) {
        screens.forEach { screen ->
            AddItem(screen = screen, currentDestination = currentDestination, navController = navHost)
        }
    }


}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
){
    val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
   /* val background1 =
        if(selected) Purple40.copy(alpha = 0.6f) else Color.TRANSPARENT*/
    val contentColor =
        if (selected)Color.WHITE else Color.BLACK

    Box(
        modifier = Modifier
            .height(45.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primaryContainer)
            .clickable {
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            }
    ){
        Row(modifier = Modifier.padding(start = 44.dp, end = 44.dp, top = 10.dp, bottom = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(painter = painterResource(id = if (selected) screen.icon_focused else screen.icon),
                contentDescription = "icon"
                )
            AnimatedVisibility(visible = selected) {
                Text(text = screen.title
                    )
            }
        }
    }
}


