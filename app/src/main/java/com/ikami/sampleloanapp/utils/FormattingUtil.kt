package com.ikami.sampleloanapp.utils

import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

object FormattingUtil {

    fun formatDate(dateToFormat: Long): String{
        val date = Date(dateToFormat)
        val format = SimpleDateFormat("EEE dd MMM yyyy")
        return format.format(date)
    }
    fun formatAmount(amount: Double): String {
        val formatter: NumberFormat = DecimalFormat("#,###")

        return formatter.format(amount)
    }
}