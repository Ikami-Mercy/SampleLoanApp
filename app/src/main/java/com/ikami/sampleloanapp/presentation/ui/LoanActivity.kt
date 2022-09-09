package com.ikami.sampleloanapp.presentation.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.ikami.sampleloanapp.R
import com.ikami.sampleloanapp.databinding.ActivityLoanBinding
import com.ikami.sampleloanapp.domain.models.NetworkResponse
import com.ikami.sampleloanapp.domain.models.UserLoanInfo
import com.ikami.sampleloanapp.presentation.viewModels.LoanViewModel
import com.ikami.sampleloanapp.utils.FormattingUtil.formatAmount
import com.ikami.sampleloanapp.utils.FormattingUtil.formatDate
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoanBinding
    private val viewModel: LoanViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loan)
        binding = ActivityLoanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeUserCurrentLoan()
    }

    private fun observeUserCurrentLoan() {
        viewModel.loanLiveData.observe(this, Observer {
            when (it) {
                is NetworkResponse.Loading -> binding.progressBar.isVisible = true
                is NetworkResponse.Success -> {
                    binding.progressBar.isVisible = false
                    processUiToDisplay(it.data)
                }
                is NetworkResponse.Error -> {
                    binding.progressBar.isVisible = false
                    Toast.makeText(this,getString(R.string.an_error_occurred),Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun processUiToDisplay(returnedUserLoan: UserLoanInfo) {
        when (returnedUserLoan.userLoan.loan?.status) {
            "due" -> {
                binding.loanDueCard.isVisible = true
                binding.loanStatusCard.isVisible = false
                binding.tvDueAmount.text =
                    "${returnedUserLoan.locale.currency}${formatAmount(returnedUserLoan.userLoan.loan.due)}"
                binding.tvDueDate.text =
                    "${getString(R.string.is_due)}${formatDate(returnedUserLoan.userLoan.loan.dueDate)}"
                binding.tvLoanLimit.text =
                    "${getString(R.string.grow_limit)} ${returnedUserLoan.locale.currency} ${
                        formatAmount(
                            returnedUserLoan.locale.loanLimit
                        )
                    }"
            }
            "approved" -> {
                binding.loanDueCard.isVisible = false
                binding.loanStatusCard.isVisible = true
                binding.tvLoanLimit.text =
                    "${getString(R.string.grow_limit)} ${returnedUserLoan.locale.currency} ${
                        formatAmount(
                            returnedUserLoan.locale.loanLimit
                        )
                    }"
                binding.tvLoanTittle.text = getString(R.string.loan_approved)
                binding.roundedLoanImageView.setImageResource(R.drawable.img_loan_status_approved)
                binding.tvLoanHeadline.text =
                    "${getString(R.string.approved_for)} ${returnedUserLoan.locale.currency} ${
                        formatAmount(
                            returnedUserLoan.userLoan.loan.approved
                        )
                    }"
                binding.btnActionLoan.text = getString(R.string.view_offer)
            }
            "paid" -> {
                binding.loanDueCard.isVisible = false
                binding.loanStatusCard.isVisible = true
                binding.roundedLoanImageView.setImageResource(R.drawable.img_loan_status_paidoff)
                binding.tvLoanTittle.text = getString(R.string.loan_paid)
                binding.tvLoanHeadline.text = getString(R.string.apply_loan)
                binding.btnActionLoan.text = getString(R.string.apply_now)
                binding.tvLoanLimit.text =
                    "${getString(R.string.grow_limit)} ${returnedUserLoan.locale.currency} ${
                        formatAmount(
                            returnedUserLoan.locale.loanLimit
                        )
                    }"
            }
            else -> {
                binding.loanDueCard.isVisible = false
                binding.loanStatusCard.isVisible = true
                binding.roundedLoanImageView.setImageResource(R.drawable.img_loan_status_apply)
                binding.tvLoanTittle.text = getString(R.string.apply_for_loan)
                binding.tvLoanLimit.text = getString(R.string.track_progress)
                binding.btnActionLoan.text = getString(R.string.apply_now)
                binding.tvLoanHeadline.text =
                    "${getString(R.string.repay_loan)} ${returnedUserLoan.locale.currency} ${
                        formatAmount(
                            returnedUserLoan.locale.loanLimit
                        )
                    }"
            }
        }
        when (returnedUserLoan.userLoan.loan?.level) {
            "gold" -> {
                binding.roundedLoanLevelImage.setImageResource(R.drawable.img_gold_badge_large)
            }
            "silver" -> {
                binding.roundedLoanLevelImage.setImageResource(R.drawable.img_silver_badge_large)
            }
            "bronze" -> {
                binding.roundedLoanLevelImage.setImageResource(R.drawable.img_bronze_badge_large)
            }
            else -> {
                binding.roundedLoanLevelImage.setImageResource(R.drawable.img_blue_badge_large)
            }
        }
        when (returnedUserLoan.userLoan.locale) {
            "ke" -> {
                binding.advertImageView.setImageResource(R.drawable.img_story_card_ke)
            }
            "ph" -> {
                binding.advertImageView.setImageResource(R.drawable.img_story_card_ph)
            }
            else -> {
                binding.advertImageView.setImageResource(R.drawable.img_story_card_mx)
            }
        }
    }
}