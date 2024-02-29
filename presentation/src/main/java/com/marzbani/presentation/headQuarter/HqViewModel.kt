package com.marzbani.presentation.headQuarter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marzbani.domain.entity.TreeNodeEntity
import com.marzbani.domain.usecase.GetNodesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HqViewModel @Inject constructor(private val getNodesUseCase: GetNodesUseCase): ViewModel() {

    private val _nodesData = MutableLiveData<List<TreeNodeEntity>>()
    val nodesData : LiveData<List<TreeNodeEntity>> get() = _nodesData


    init {
        loadData()
    }

    private fun loadData()
    {
        getNodesUseCase.execute(
            params = "data.json",
            onSuccess = {
                _nodesData.value=it
            },
            onError = {
                it.printStackTrace()
            },

        )
    }
}