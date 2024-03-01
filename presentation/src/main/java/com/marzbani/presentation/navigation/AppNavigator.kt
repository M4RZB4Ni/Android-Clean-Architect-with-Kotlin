package com.marzbani.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.marzbani.presentation.details.DetailsScreen
import com.marzbani.presentation.details.DetailsViewModel
import com.marzbani.presentation.nodes.NodesViewModel
import com.marzbani.presentation.nodes.NodesScreen


@Composable
    fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.NODES.route,
    ) {
        NavHost(
            navController = navController,
            startDestination = startDestination
        ) {
            composable(NavigationItem.NODES.route,) {
                val viewModel = hiltViewModel<NodesViewModel>()
                NodesScreen(modifier,viewModel,navController)
            }
            composable(
                route = "${NavigationItem.DETAILS.route}/{nodeID}",
                arguments = listOf(navArgument("nodeID") { type = NavType.StringType })
            ) { backStackEntry ->
                val nodeId = backStackEntry.arguments?.getString("nodeID") ?: ""
                val viewModel = hiltViewModel<DetailsViewModel>()
                DetailsScreen(modifier, viewModel, nodeId,navController)
            }

        }
    }
