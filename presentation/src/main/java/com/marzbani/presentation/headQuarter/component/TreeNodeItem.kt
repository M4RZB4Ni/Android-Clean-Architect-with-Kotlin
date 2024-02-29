package com.marzbani.presentation.headQuarter.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
    treeNode: TreeNodeEntity,
    onItemClick: (TreeNodeEntity) -> Unit,
    onRemoveClick: (TreeNodeEntity) -> Unit,
    onMoveClick: (TreeNodeEntity) -> Unit,
    isEditMode: Boolean
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (isEditMode) 4.dp else 0.dp
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .clickable { onItemClick(treeNode) }
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
        ) {
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
                Text(
                    text = treeNode.label,
                )
            }

            // Recursively display children
            treeNode.children?.let { children ->
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    children.forEach { childNode ->
                        TreeNodeItem(
                            treeNode = childNode,
                            onItemClick = onItemClick,
                            onRemoveClick = onRemoveClick,
                            onMoveClick = onMoveClick,
                            isEditMode = isEditMode
                        )
                    }
                }
            }

            // Display edit actions when in edit mode
            if (isEditMode) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Button to remove the item
                    IconButton(onClick = { onRemoveClick(treeNode) }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Remove",
                            tint = MaterialTheme.colorScheme.error
                        )
                    }

                    // Button to move the item
                    IconButton(onClick = { onMoveClick(treeNode) }) {
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = "Move",
                            tint = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            }
        }
    }
}