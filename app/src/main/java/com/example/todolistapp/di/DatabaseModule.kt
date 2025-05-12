package com.example.todolistapp.di

import android.content.Context
import androidx.room.Room
import com.example.todolistapp.data.local.AppDatabase
import com.example.todolistapp.data.local.ToDoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context):
            AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "todo_database"
        ).build()
    }

    @Provides
    fun provideToDoDao(appDatabase: AppDatabase): ToDoDao{
        return appDatabase.ToDoDao()
    }
}