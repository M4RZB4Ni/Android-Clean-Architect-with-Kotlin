package com.marzbani.presentation.headQuarter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.marzbani.domain.entity.TreeNodeEntity
import com.marzbani.presentation.headQuarter.component.TreeNodeItem
import com.marzbani.presentation.ui.theme.ImglyTaskTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.StateFlow


@AndroidEntryPoint
class HqActivity : ComponentActivity() {

private val viewModel:HqViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImglyTaskTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    TreeScreen(nodesData = viewModel.nodesData, onItemClick = {})
                }

            }
        }
    }
}

@Composable
fun TreeScreen(
    nodesData: StateFlow<List<TreeNodeEntity>>,
    onItemClick: (TreeNodeEntity) -> Unit
) {
    val data by nodesData.collectAsState()
    if(data.isNotEmpty()) {
        LazyColumn()
        {
            items(data)
            {
                TreeNodeItem(treeNode =it , onItemClick = onItemClick)
            }
        }
    }else{
        CircularProgressIndicator()
    }

}

