package com.marzbani.presentation.navigation


enum class Screens {
    Nodes,
    DETAILS,
}

sealed class NavigationItem(val route: String) {
    data object Nodes : NavigationItem(Screens.Nodes.name)
    data object DETAILS : NavigationItem(Screens.DETAILS.name)
}
