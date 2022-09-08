package com.ikami.sampleloanapp.di

import com.ikami.sampleloanapp.data.LoanRepository
import com.ikami.sampleloanapp.domain.repositories.ILoanRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindLoanRepository(loanRepository: LoanRepository): ILoanRepository

}