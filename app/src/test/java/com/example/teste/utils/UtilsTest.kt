package com.example.teste.utils

import com.example.teste.modules.Utils
import org.junit.Assert
import org.junit.jupiter.api.Test

class UtilsTest {

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
}