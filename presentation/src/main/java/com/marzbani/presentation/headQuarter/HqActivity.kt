package com.marzbani.presentation.headQuarter

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.marzbani.presentation.headQuarter.component.TreeNodeItem


@OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun NodesScreen(
        modifier: Modifier,
        viewModel: HqViewModel,
    ) {

        val data by viewModel.nodesData.collectAsState()


                Scaffold(topBar = { TopAppBar(
                    title = { Text(text = "Task Test") },
                    actions = {
                        IconButton(
                            onClick = { viewModel.toggleEditMode() },
                            modifier = Modifier.padding(end = 16.dp)
                        ) {
                            Icon(
                                imageVector = if (viewModel.isEditMode) Icons.Default.Check else Icons.Default.Edit,
                                contentDescription = if (viewModel.isEditMode) "Save" else "Edit"
                            )
                        }
                    }
                ) }) { paddingValues ->
                    LazyColumn(
                        modifier = modifier.padding(paddingValues),
                        content = {
                            items(data) { treeNode ->
                                TreeNodeItem(
                                    modifier = modifier,
                                    treeNode = treeNode,
                                    onItemClick = {viewModel.onItemClick(it)},
                                    onRemoveClick = {viewModel.onRemoveClick(it)},
                                    onMoveClick = { movedNode,parentNode-> viewModel.onMoveClick(movedNode,parentNode)},
                                    isEditMode = viewModel.isEditMode
                                )
                            }
                        }
                    )
                }

    }



