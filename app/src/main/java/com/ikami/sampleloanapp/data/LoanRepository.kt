package com.ikami.sampleloanapp.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ikami.sampleloanapp.domain.models.*
import com.ikami.sampleloanapp.domain.repositories.ILoanRepository
import java.io.IOException
import javax.inject.Inject
import kotlin.random.Random

class LoanRepository @Inject constructor(
    private val context: Context
) : ILoanRepository {

    private fun loadLoans(): Response<List<UserLoan>?> {
        lateinit var jsonString: String
        try {
            jsonString = context.assets.open("testData.json")
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            return Response(false, ioException, null)
        }
        val listLoanType = object : TypeToken<List<UserLoan>>() {}.type
        return Response(true, null, Gson().fromJson(jsonString, listLoanType))
    }

    private fun loadLoanLocales(): Response<CountryLocale?> {
        lateinit var jsonString: String
        try {
            jsonString = context.assets.open("locales.json")
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            return Response(false, ioException, null)
        }
        val loanLimitType = object : TypeToken<CountryLocale>() {}.type
        return Response(true, null, Gson().fromJson(jsonString, loanLimitType))
    }

    private fun getARandomUserLoan(loansList: List<UserLoan>): UserLoan {
        val randomIndex = Random.nextInt(loansList.size)
        return loansList[randomIndex]
    }

    private fun getALoanLocale(userLoan: UserLoan): Locale {
        return when (userLoan.locale) {
            "ke" -> {
                loadLoanLocales().body!!.ke
            }
            "ph" -> {
                loadLoanLocales().body!!.ph
            }
            else -> {
                loadLoanLocales().body!!.mx
            }
        }
    }

    override suspend fun fetchCurrentUserLoanInfo(): NetworkResponse<UserLoanInfo> {
        return try {
            val loanListsResult = loadLoans()
            if(loanListsResult.wasSuccessful){
                val randomUserLoan =
                    loanListsResult.body?.let { getARandomUserLoan(loanListsResult.body) }
                val loanLimit = randomUserLoan?.let { getALoanLocale(it) }
                NetworkResponse.Success(UserLoanInfo(randomUserLoan!!, loanLimit!!))
            }
            else{
                NetworkResponse.Error(loanListsResult.exception?.message!!)
            }
        } catch (e: Exception) {
            NetworkResponse.Error(e.message.toString())
        }
    }
}
