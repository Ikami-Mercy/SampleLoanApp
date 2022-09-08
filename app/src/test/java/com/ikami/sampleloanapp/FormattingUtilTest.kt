package com.ikami.sampleloanapp

import com.ikami.sampleloanapp.utils.FormattingUtil
import org.junit.Assert
import org.junit.Test

class FormattingUtilTest {
    @Test
    fun formatDateShouldReturnTheRightDateFormat () {
        var utils =FormattingUtil
        var date = utils.formatDate(1562601600000)
        Assert.assertEquals("Mon 08 Jul 2019", date)
    }

    @Test
    fun format3DigitAmountShouldReturnTheRightAmountFormat () {
        var utils =FormattingUtil
        var amount = utils.formatAmount(740.0)
        Assert.assertEquals("740", amount)
    }
    @Test
    fun format4DigitAmountShouldReturnTheRightAmountFormat () {
        val utils =FormattingUtil
        val amount = utils.formatAmount(3456.0)
        Assert.assertEquals("3,456", amount)
    }
}