package com.ikami.sampleloanapp.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ikami.sampleloanapp.domain.models.NetworkResponse
import com.ikami.sampleloanapp.domain.models.UserLoanInfo
import com.ikami.sampleloanapp.domain.repositories.ILoanRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoanViewModel @Inject constructor(
    private val repository: ILoanRepository,
) : ViewModel() {
    private var mutableLoanLiveData: MutableLiveData<NetworkResponse<UserLoanInfo>> =
        MutableLiveData()
    var loanLiveData: LiveData<NetworkResponse<UserLoanInfo>> = mutableLoanLiveData

    init {
        fetchCurrentUserLoanInfo()
    }

    fun fetchCurrentUserLoanInfo() {
        viewModelScope.launch {
            mutableLoanLiveData.value = repository.fetchCurrentUserLoanInfo()

        }

    }
}

