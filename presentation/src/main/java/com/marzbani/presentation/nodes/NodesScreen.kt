package com.marzbani.presentation.nodes

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.marzbani.presentation.nodes.component.MainAppBar
import com.marzbani.presentation.nodes.component.TreeNodeItem


@Composable
fun NodesScreen(
    modifier: Modifier,
    viewModel: NodesViewModel,
    navController: NavHostController
) {
    val data by viewModel.nodesData.collectAsState()

    Scaffold(
        topBar = {
            MainAppBar(
                onEditClick = { viewModel.toggleEditMode() },
                modifier = Modifier
                    .padding(16.dp),
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier.padding(paddingValues).padding(start = 16.dp, end = 16.dp),
            content = {
                items(data) { treeNode ->
                    TreeNodeItem(
                        modifier = modifier,
                        treeNode = treeNode,
                        onItemClick = {
                            viewModel.onItemClick(it,navController)

                        },
                        onRemoveClick = { viewModel.onRemoveClick(it) },
                        onMoveClick = { movedNode, parentNode -> viewModel.onMoveClick(movedNode, parentNode) },
                        isEditMode = viewModel.isEditMode
                    )
                }
            }
        )
    }
}


