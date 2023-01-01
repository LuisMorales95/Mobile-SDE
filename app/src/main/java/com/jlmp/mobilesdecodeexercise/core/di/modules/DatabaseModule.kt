package com.jlmp.mobilesdecodeexercise.core.di.modules

import android.content.Context
import androidx.room.Room
import com.jlmp.mobilesdecodeexercise.core.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun providesDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java,"DB_MOBILE_SDE")
            .fallbackToDestructiveMigration()
            .build()
    }

}