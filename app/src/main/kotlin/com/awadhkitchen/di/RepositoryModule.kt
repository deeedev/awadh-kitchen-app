package com.awadhkitchen.di

import com.awadhkitchen.data.repository.AuthRepository
import com.awadhkitchen.data.repository.AuthRepositoryImpl
import com.awadhkitchen.data.repository.SubscriptionRepository
import com.awadhkitchen.data.repository.SubscriptionRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    @Singleton
    abstract fun bindSubscriptionRepository(
        subscriptionRepositoryImpl: SubscriptionRepositoryImpl
    ): SubscriptionRepository
}
