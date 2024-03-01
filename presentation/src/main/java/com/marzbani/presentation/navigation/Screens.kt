package com.marzbani.presentation.navigation


enum class Screens {
    NODES,
    DETAILS,
}

sealed class NavigationItem(val route: String) {
    data object NODES : NavigationItem(Screens.NODES.name,)
    data object DETAILS : NavigationItem(Screens.DETAILS.name)
}
