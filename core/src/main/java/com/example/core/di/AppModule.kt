package com.example.core.di

import com.example.core.coroutines.CoroutineContextOwner
import com.example.core.coroutines.StandardCoroutineContexts
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineExceptionHandler
import timber.log.Timber
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object AppModule {

    @Singleton
    @Provides
    fun provideCoroutineExceptionHandler(): CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            Timber.e(throwable, "asdasd")
        }

    @Singleton
    @Provides
    fun provideCoroutineContextOwner(
        exceptionHandler: CoroutineExceptionHandler
    ): CoroutineContextOwner = StandardCoroutineContexts(
        exceptionHandler = exceptionHandler
    )
}
