package com.marzbani.presentation.headQuarter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.lifecycle.LiveData
import com.marzbani.domain.entity.TreeNodeEntity
import com.marzbani.presentation.ui.theme.ImglyTakskTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HqActivity : ComponentActivity() {

private val viewModel:HqViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImglyTakskTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }
            }
        }
    }
}

@Composable
fun TreeScreen(
    nodesData: LiveData<List<TreeNodeEntity>>,
    onItemClick: (TreeNodeEntity) -> Unit
) {
    val data by nodesData.observeAsState()
    LazyColumn() {
        itemsIndexed(data ?: emptyList()) { index, treeNodeEntity ->
            key("${treeNodeEntity.label}_$index") {
                ClickableText(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                            append(treeNodeEntity.label)
                        }
                    },
                    onClick = {
                        onItemClick(treeNodeEntity)
                    }
                )
            }
            }
        }


}

