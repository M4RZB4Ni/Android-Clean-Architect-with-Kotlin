package com.marzbani.presentation.details

import androidx.lifecycle.ViewModel
import com.marzbani.domain.usecase.GetDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val detailsUseCase: GetDetailsUseCase):ViewModel() {

}