package com.example.omangarchsample.di

import android.content.Context
import com.example.omangarchsample.OmangArchApplication



import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext context: Context): OmangArchApplication {
        return context as OmangArchApplication
    }
}
