package com.marzbani.presentation.headQuarter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import com.marzbani.domain.entity.TreeNodeEntity
import com.marzbani.presentation.headQuarter.component.TreeNodeItem
import com.marzbani.presentation.ui.theme.ImglyTaskTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.StateFlow


@AndroidEntryPoint
class HqActivity : ComponentActivity() {

private val viewModel:HqViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImglyTaskTheme {
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
                    TreeScreen(Modifier.padding(paddingValues),nodesData = viewModel.nodesData, onItemClick={viewModel.onItemClick(it)},onRemoveClick={it->viewModel.onRemoveClick(it) }, onMoveClick = { movedNode,parentNode-> viewModel.onMoveClick(movedNode,parentNode)}, isEditMode = viewModel.isEditMode)
            }
        }
    }
}

    @Composable
    fun TreeScreen(
        modifier: Modifier,
        nodesData: StateFlow<List<TreeNodeEntity>>,
        onItemClick: (TreeNodeEntity) -> Unit,
        onRemoveClick: (TreeNodeEntity) -> Unit,
        onMoveClick: (TreeNodeEntity, TreeNodeEntity?) -> Unit,
        isEditMode: Boolean
    ) {
        val data by nodesData.collectAsState()

        LazyColumn(
            modifier = modifier,
            content = {
                items(data) { treeNode ->
                    TreeNodeItem(
                        modifier = modifier,
                        treeNode = treeNode,
                        onItemClick = onItemClick,
                        onRemoveClick = onRemoveClick,
                        onMoveClick = onMoveClick,
                        isEditMode = isEditMode
                    )
                }
            }
        )
    }

}

