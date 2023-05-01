package com.kodeco.daysprint.di

import android.app.Application
import androidx.room.Room
import com.kodeco.daysprint.data.TaskDataSource
import com.kodeco.daysprint.data.TaskDatabase
import com.kodeco.daysprint.data.TaskLocalDataSource
import com.kodeco.daysprint.data.TaskRepository
import dagger.Binds
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
            "task_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideTodoRepository(db: TaskLocalDataSource): TaskRepository {
        return TaskRepository(db)
    }

    @Provides
    @Singleton
    fun provideDao(db: TaskDatabase) = db.dao
}

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    abstract fun bindTaskDataSource(
        taskLocalDataSource: TaskLocalDataSource
    ): TaskDataSource

}