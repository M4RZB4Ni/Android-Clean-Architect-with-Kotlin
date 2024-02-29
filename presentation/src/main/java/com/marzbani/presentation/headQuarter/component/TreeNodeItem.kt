package com.marzbani.presentation.headQuarter.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.marzbani.domain.entity.TreeNodeEntity

@Composable
fun TreeNodeItem(treeNode: TreeNodeEntity, onItemClick: (TreeNodeEntity) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp), // Adjust the corner radius as needed
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ), // Use your specific CardElevation property here
    ) {
        Column(
            modifier = Modifier
                .clickable { onItemClick(treeNode) }
                .padding(16.dp)
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
                    modifier = Modifier
                        .size(24.dp) // Adjust the icon size as needed
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f), shape = CircleShape)
                        .padding(4.dp) // Adjust the padding as needed
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = treeNode.label,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            // Recursively display children
            treeNode.children?.let { children ->
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    children.forEach { childNode ->
                        // Use inner Card for each child with different colors
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .background(MaterialTheme.colorScheme.secondaryContainer, shape = RoundedCornerShape(12.dp)), // Adjust the corner radius and color

                            shape = RoundedCornerShape(12.dp), // Adjust the corner radius as needed
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 10.dp
                            ), // Use your specific InnerCardElevation property here
                        ) {
                            TreeNodeItem(treeNode = childNode, onItemClick = onItemClick)
                        }
                    }
                }
            }
        }
    }
}








