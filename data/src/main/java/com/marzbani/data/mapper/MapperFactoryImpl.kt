package com.marzbani.data.mapper

import com.marzbani.data.di.MapperFactory

class MapperFactoryImpl : MapperFactory {
    override fun provideTreeNodeEntityMapper(): TreeNodeEntityMapper {
        return TreeNodeEntityMapper()
    }

    override fun provideDetailsEntityMapper(): DetailsEntityMapper {
        return DetailsEntityMapper()
    }
}