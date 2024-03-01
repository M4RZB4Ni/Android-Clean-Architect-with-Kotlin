package com.marzbani.presentation.nodes

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.marzbani.domain.entity.TreeNodeEntity
import com.marzbani.domain.usecase.GetNodesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class NodesViewModel @Inject constructor(private val getNodesUseCase: GetNodesUseCase): ViewModel() {

    private val _nodesData = MutableStateFlow<List<TreeNodeEntity>>(emptyList())
    val nodesData : StateFlow<List<TreeNodeEntity>> get() = _nodesData

    private var _isEditMode by mutableStateOf(false)
    val isEditMode: Boolean get() = _isEditMode


    fun toggleEditMode() {
        _isEditMode = !_isEditMode
    }

    init {
        loadData()
    }

    private fun loadData()
    {
        getNodesUseCase.execute(
            params = "data.json",
            onSuccess = {
                Log.d("loadData",it.toString())
                _nodesData.value=it
            },
            onError = {
                it.printStackTrace()
            },

        )
    }
    fun onRemoveClick(selectedNode: TreeNodeEntity) {
        _nodesData.value = removeNodeRecursively(_nodesData.value, selectedNode)
    }
    private fun removeNodeRecursively(nodes: List<TreeNodeEntity>, targetNode: TreeNodeEntity): List<TreeNodeEntity> {
        return nodes.filterNot { it == targetNode }
            .map { it.copy(children = removeNodeRecursively(it.children.orEmpty(), targetNode)) }
    }
    fun onItemClick(selectedNode: TreeNodeEntity,navController: NavController) {
        Log.e("selectedNode",selectedNode.id.toString())
        navController.navigate("details/${selectedNode.id}")
    }
    fun onMoveClick(movedNode: TreeNodeEntity, newParentNode: TreeNodeEntity?) {
        println("Before Move:")
        println("Moved Node: $movedNode")
        println("New Parent Node: $newParentNode")
        println("Original Nodes:")
        println(_nodesData.value)

        val updatedNodes = moveNode(_nodesData.value, movedNode, newParentNode)

        println("After Move:")
        println("Updated Nodes:")
        println(updatedNodes)

        _nodesData.value = updatedNodes
    }

    private fun moveNode(nodes: List<TreeNodeEntity>, movedNode: TreeNodeEntity, newParentNode: TreeNodeEntity?): List<TreeNodeEntity> {
        return nodes.map { node ->
            when (node) {
                movedNode -> {
                    node.copy(children = moveNode(node.children.orEmpty(), movedNode, null))
                }
                newParentNode -> {
                    node.copy(children = node.children.orEmpty() + movedNode)
                }
                else -> {
                    node.copy(children = moveNode(node.children.orEmpty(), movedNode, newParentNode))
                }
            }
        }.filterNot { it == movedNode }
    }
}