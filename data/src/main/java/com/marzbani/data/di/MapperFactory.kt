package com.marzbani.data.di

import com.marzbani.data.mapper.DetailsEntityMapper
import com.marzbani.data.mapper.TreeNodeEntityMapper

interface MapperFactory {
    fun provideTreeNodeEntityMapper(): TreeNodeEntityMapper
    fun provideDetailsEntityMapper(): DetailsEntityMapper
}