package com.example.app.ui.navigation

import com.example.app.R

sealed class BottomBarScreen(
    val route: String,
    var title: String,
    val icon: Int,
    val icon_focused: Int
){
    object Inicio: BottomBarScreen(
        route="inicio",
        title="Inicio",
        icon= R.drawable.baseline_home_24,
        icon_focused = R.drawable.home
    )
    object Nuevo: BottomBarScreen(
        route="nuevo",
        title="Nuevo",
        icon= R.drawable.add,
        icon_focused = R.drawable.add
    )
    object Milista: BottomBarScreen(
        route="milista",
        title="Mi lista",
        icon= R.drawable.list,
        icon_focused = R.drawable.list
    )

}
