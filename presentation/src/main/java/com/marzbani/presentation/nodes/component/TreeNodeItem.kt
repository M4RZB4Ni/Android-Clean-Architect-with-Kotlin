package com.marzbani.presentation.nodes.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.marzbani.domain.entity.TreeNodeEntity

@Composable
fun TreeNodeItem(
    modifier: Modifier,
    treeNode: TreeNodeEntity,
    onItemClick: (TreeNodeEntity) -> Unit,
    onRemoveClick: (TreeNodeEntity) -> Unit,
    onMoveClick: (TreeNodeEntity, TreeNodeEntity?) -> Unit,
    isEditMode: Boolean
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation =  CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(
            modifier = Modifier
                .clickable { onItemClick(treeNode) }
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                // Content of the TreeNode
                TreeNodeContent(treeNode = treeNode, onItemClick = onItemClick)

                // Recursively display children using LazyColumn
                treeNode.children?.let { children ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        children.forEach { childNode ->
                            TreeNodeItem(
                                modifier = Modifier,
                                treeNode = childNode,
                                onItemClick = onItemClick,
                                onRemoveClick = onRemoveClick,
                                onMoveClick = { movedNode, newParentNode ->
                                    onMoveClick(movedNode, newParentNode)
                                },
                                isEditMode = isEditMode
                            )
                        }
                    }
                }

                // Display edit actions when in edit mode
                if (isEditMode) {
                    TreeNodeEditActions(
                        onRemoveClick = { onRemoveClick(treeNode) },
                        onMoveClick = { onMoveClick(treeNode, null) } // Pass null for newParentNode initially
                    )
                }
            }
        }
    }
}



