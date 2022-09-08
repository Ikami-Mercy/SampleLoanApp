package com.ikami.sampleloanapp.di

import android.content.Context
import com.ikami.sampleloanapp.data.LoanRepository
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
    fun provideLoanRepository(@ApplicationContext context: Context): LoanRepository = LoanRepository(context)
}