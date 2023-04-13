package com.kodeco.daysprint.di

import android.app.Application
import androidx.room.Room
import com.kodeco.daysprint.data.TaskDatabase
import com.kodeco.daysprint.data.TaskRepository
import com.kodeco.daysprint.data.TaskRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTodoDatabase(app: Application): TaskDatabase {
        return Room.databaseBuilder(
            app,
            TaskDatabase::class.java,
            "todo_db"
        ).build()
    }


    @Provides
    @Singleton
    fun provideTodoRepository(db: TaskDatabase): TaskRepository {
        return TaskRepositoryImpl(db.dao)
    }
}