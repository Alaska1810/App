package com.example.app.ui.navigation

sealed class Screens(
    val route : String) {
    object SplashScreen : Screens("SplashScreen")
    object Login : Screens("Login")
    //object InicioScreen : Screens("InicioScreen")
    object BottomNav : Screens("BottomNav")
    //object Agregar : Screens("Agregar")
    object Recordatorio : Screens("Recordatorio")
    object Inicio : Screens("inicio")
    object Nuevo : Screens("nuevo")
    object Milista: Screens("milista")


}