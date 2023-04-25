package com.example.omangarchsample.di


import android.content.Context
import com.example.omangarchsample.database.OmangDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): OmangDatabase = OmangDatabase.getDatabase(appContext)
    @Singleton
    @Provides
    fun provideUserInfoDao(db: OmangDatabase) = db.userInfoDao()


}
