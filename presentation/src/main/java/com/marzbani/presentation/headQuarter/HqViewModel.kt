package com.marzbani.presentation.headQuarter

import android.util.Log
import androidx.lifecycle.ViewModel
import com.marzbani.domain.entity.TreeNodeEntity
import com.marzbani.domain.usecase.GetNodesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HqViewModel @Inject constructor(private val getNodesUseCase: GetNodesUseCase): ViewModel() {

    private val _nodesData = MutableStateFlow<List<TreeNodeEntity>>(emptyList())
    val nodesData : StateFlow<List<TreeNodeEntity>> get() = _nodesData


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
}