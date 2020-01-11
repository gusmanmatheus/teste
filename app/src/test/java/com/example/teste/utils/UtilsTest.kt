package com.example.teste.utils

import android.content.Context
import android.widget.EditText
import com.example.teste.modules.Utils
import io.mockk.mockk
import okhttp3.internal.Util
import org.junit.Assert
import org.junit.jupiter.api.Test

class UtilsTest {
    private val context: Context = mockk()

    @Test
    fun `testMaskAppliDataWith1Number`() {
        val date = "022"
        val mask = Utils.maskDate(date)
        Assert.assertEquals(mask, "02/2")
    }

    @Test
    fun `testMaskAppliDataWith2Number`() {
        val date = "0222"
        val mask = Utils.maskDate(date)
        Assert.assertEquals(mask, "02/22")
    }

    @Test
    fun `testMaskAppliDataWith3Number`() {
        val date = "02222"
        val mask = Utils.maskDate(date)
        Assert.assertEquals(mask, "02222")
    }

    @Test
    fun `testMakNumberCardComplete`() {
        val numberCard = "1111111111111111"
        val mask = Utils.maskNumberCard(numberCard)
        Assert.assertEquals(mask, "1111  1111  1111  1111")
    }

    @Test
    fun `testMakNumberCard`() {
        val numberCard = "11111111111111"
        val mask = Utils.maskNumberCard(numberCard)
        Assert.assertEquals(mask, "1111  1111  1111  11")
    }

    @Test
    fun `unMaskMoney`() {
        val value = "111.111,00"
        val unmask = Utils.cleanMoneyText(value).toString()
        Assert.assertEquals(unmask, "111111.0")

    }

    @Test
    fun `unMaskMoneySecond`() {
        val value = "111,00"
        val unmask = Utils.cleanMoneyText(value).toString()
        Assert.assertEquals(unmask, "111.0")

    }
}