package com.example.app.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.app.ui.screens.Recordatorio1
import com.example.app.login.Login
import com.example.app.login.LoginScreenViewModel
import com.example.app.splash.SplashScreen
import com.example.app.ui.screens.Agregar
import com.example.app.ui.screens.RecordatorioViewModel


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavGraph(
    navHost: NavHostController,
    recordatorioViewModel: RecordatorioViewModel
)
{
   // val navController = rememberNavController()
    val viewModel = LoginScreenViewModel()

    NavHost(
        navController = navHost,
        startDestination = Screens.SplashScreen.route
    ){
        composable(Screens.SplashScreen.route){
            SplashScreen(navHost = navHost)
        }
        composable(Screens.Login.route){
            Login(viewModel = viewModel, navHost = navHost)
        }
        composable(Screens.BottomNav.route){
            BottomNav(navHost = navHost, viewModel = recordatorioViewModel)
        }
        composable(Screens.Inicio.route){
            BottomNav(navHost = navHost, viewModel = recordatorioViewModel)
        }
        composable(Screens.Nuevo.route){
            Agregar(navHost = navHost, viewModel = recordatorioViewModel)
        }
        composable(Screens.Milista.route){
            Recordatorio1(navHost = navHost, viewModel = recordatorioViewModel)
        }
        composable(Screens.Recordatorio.route){
            Recordatorio1(navHost = navHost, viewModel = recordatorioViewModel)
        }

    }
}