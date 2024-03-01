package com.marzbani.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.marzbani.presentation.details.DetailsActivity
import com.marzbani.presentation.details.DetailsViewModel
import com.marzbani.presentation.headQuarter.HqViewModel
import com.marzbani.presentation.headQuarter.NodesScreen


@Composable
    fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Nodes.route,
    ) {
        NavHost(
            navController = navController,
            startDestination = startDestination
        ) {
            composable(NavigationItem.Nodes.route) {
                val viewModel = hiltViewModel<HqViewModel>()
                NodesScreen(modifier,viewModel)
            }
            composable(NavigationItem.DETAILS.route) {
                val viewModel = hiltViewModel<DetailsViewModel>()

                DetailsActivity()
            }
        }
    }
