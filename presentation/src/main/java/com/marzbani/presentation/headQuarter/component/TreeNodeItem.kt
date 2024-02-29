package com.marzbani.presentation.headQuarter.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.marzbani.domain.entity.TreeNodeEntity

@Composable
fun TreeNodeItem(treeNode: TreeNodeEntity, onItemClick: (TreeNodeEntity) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(treeNode) }
            .padding(8.dp)
    ) {
        Text(text = treeNode.label, modifier = Modifier.padding(8.dp))
    }
}