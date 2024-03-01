package com.marzbani.presentation.details

import androidx.lifecycle.ViewModel
import com.marzbani.domain.entity.DetailsEntity
import com.marzbani.domain.usecase.GetDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val detailsUseCase: GetDetailsUseCase):ViewModel() {

    private val _details =MutableStateFlow(DetailsEntity())
    val details: StateFlow<DetailsEntity> get() = _details
    fun getDetails(nodeId:String)
    {
        detailsUseCase.execute(
            params = nodeId,
            onSuccess = {
                _details.value=it
            },
            onError = {
                it.printStackTrace()
            }
        )
    }

}