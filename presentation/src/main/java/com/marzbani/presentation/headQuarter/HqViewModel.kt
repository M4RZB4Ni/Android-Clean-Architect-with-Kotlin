package com.marzbani.presentation.headQuarter

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.marzbani.domain.entity.TreeNodeEntity
import com.marzbani.domain.entity.moveNode
import com.marzbani.domain.entity.removeNode
import com.marzbani.domain.usecase.GetNodesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HqViewModel @Inject constructor(private val getNodesUseCase: GetNodesUseCase): ViewModel() {

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
        val updatedNodes = removeNode(_nodesData.value, selectedNode)
        _nodesData.value = updatedNodes
    }

    fun onMoveClick(movedNode: TreeNodeEntity) {
        val updatedNodes = moveNode(_nodesData.value, movedNode)
        _nodesData.value = updatedNodes
    }

}