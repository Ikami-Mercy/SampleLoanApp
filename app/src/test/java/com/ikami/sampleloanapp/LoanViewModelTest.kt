package com.ikami.sampleloanapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.ikami.sampleloanapp.domain.models.*
import com.ikami.sampleloanapp.domain.repositories.ILoanRepository
import com.ikami.sampleloanapp.presentation.viewModels.LoanViewModel
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
class MainCoroutineRule(private val dispatcher: TestDispatcher = StandardTestDispatcher()) :
    TestWatcher() {

    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(dispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain()
    }
}

@RunWith(MockitoJUnitRunner::class)
@OptIn(ExperimentalCoroutinesApi::class)
class LoanViewModelTest{

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()
    @Mock
    private lateinit var loanRepository: ILoanRepository
    private lateinit var viewModel: LoanViewModel
    private lateinit var userLoanInfo: UserLoanInfo

    @Mock
    private lateinit var observer: Observer<NetworkResponse<UserLoanInfo>>

    @Before
    fun setUp() {
        loanRepository = mock(ILoanRepository::class.java)
        userLoanInfo = UserLoanInfo(
            UserLoan(
                Loan(2.0, 1222222L, "SILVER", "DUE", 1.0),
                "ke",
                123456L,
                "user"
            ), Locale("KES", 2.0, 6)
        )
        viewModel = LoanViewModel(loanRepository)
        viewModel.loanLiveData.observeForever(observer)

    }


    @Test
    fun `when fetchCurrentUserLoanInfo Success is emitted`() = runTest {
        `when`(loanRepository.fetchCurrentUserLoanInfo()).thenReturn(
            NetworkResponse.Success(
                userLoanInfo
            )
        )
        viewModel.fetchCurrentUserLoanInfo()
        advanceUntilIdle()
        verify(observer).onChanged(
            NetworkResponse.Success(userLoanInfo))
    }

    @Test
    fun `when fetchCurrentUserLoanInfo and an error is thrown Error is emitted`() = runTest {
        `when`(loanRepository.fetchCurrentUserLoanInfo()).thenReturn(
            NetworkResponse.Error(RuntimeException("error").toString())
        )
        viewModel.fetchCurrentUserLoanInfo()
        advanceUntilIdle()
        verify(observer).onChanged(
            NetworkResponse.Error(RuntimeException("error").toString())
        )
    }
    @Test
    fun `fetchCurrentUserLoanInfo is not null`() = runTest {
        `when`(loanRepository.fetchCurrentUserLoanInfo()).thenReturn(
            NetworkResponse.Success(
                userLoanInfo
            )
        )
        assertNotNull(viewModel.fetchCurrentUserLoanInfo())
    }

    @Test
    fun `when fetchCurrentUserLoanInfo 2 states Loading, Success are emitted`() = runTest {
        `when`(loanRepository.fetchCurrentUserLoanInfo()).thenReturn(
            NetworkResponse.Success(
                userLoanInfo
            )
        )
        viewModel.fetchCurrentUserLoanInfo()
        verify(observer,times(2))
    }
}