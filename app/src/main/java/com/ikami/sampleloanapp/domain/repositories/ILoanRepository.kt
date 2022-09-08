package com.ikami.sampleloanapp.domain.repositories

import com.ikami.sampleloanapp.domain.models.NetworkResponse
import com.ikami.sampleloanapp.domain.models.UserLoanInfo

interface ILoanRepository {
   suspend fun fetchCurrentUserLoanInfo(): NetworkResponse<UserLoanInfo>
}