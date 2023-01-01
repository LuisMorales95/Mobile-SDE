package com.jlmp.mobilesdecodeexercise.core.di.modules

import com.jlmp.mobilesdecodeexercise.core.rawfile.RawDriverParser
import com.jlmp.mobilesdecodeexercise.core.rawfile.RawFileParser
import com.jlmp.mobilesdecodeexercise.core.rawfile.RawShipmentParser
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RawFileParserModule {

    @Binds
    abstract fun bindsRawDriverParser(rawFileParser: RawFileParser): RawDriverParser

    @Binds
    abstract fun bindsRawShipmentParser(rawFileParser: RawFileParser): RawShipmentParser
}