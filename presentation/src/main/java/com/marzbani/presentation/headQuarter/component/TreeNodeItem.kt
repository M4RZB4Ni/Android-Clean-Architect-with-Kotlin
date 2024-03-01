package com.marzbani.presentation.headQuarter.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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

        elevation = CardDefaults.cardElevation(
            defaultElevation = if (isEditMode) 4.dp else 0.dp
        ),
        shape = RoundedCornerShape(8.dp)
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
                }                // Display edit actions when in edit mode
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



@Composable
private fun TreeNodeContent(treeNode: TreeNodeEntity, onItemClick: (TreeNodeEntity) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.AccountBox,
            contentDescription = null,
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = treeNode.label)
    }
}

@Composable
private fun TreeNodeEditActions(
    onRemoveClick: () -> Unit,
    onMoveClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Button to remove the item
        IconButton(onClick = onRemoveClick) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Remove",
                tint = MaterialTheme.colorScheme.error
            )
        }

        // Button to move the item
        IconButton(onClick = onMoveClick) {
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Move",
                tint = MaterialTheme.colorScheme.secondary
            )
        }
    }
}
