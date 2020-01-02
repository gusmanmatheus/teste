package com.example.teste.RegisterCardFeature

import com.example.teste.features.registerCard.RegisterCardViewModel
import io.mockk.junit5.MockKExtension
import org.junit.Assert
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class RegisterCardViewModelTest {
    private val viewModel = RegisterCardViewModel()


    @Test
    fun `test numberCard size expectancy True`() {
        val verify = viewModel.verifyNumeberCharacterNumberCard("01234567890000")//14
        Assert.assertTrue(verify)
    }

    @Test
    fun `test numberCard size with less character expectancy False`() {
        val verify = viewModel.verifyNumeberCharacterNumberCard("0123456789")//10
        Assert.assertFalse(verify)
    }

    @Test
    fun `test numberCard size with more character expectancy False`() {
        val verify = viewModel.verifyNumeberCharacterNumberCard("0123456789000000")//15
        Assert.assertFalse(verify)

    }

    @Test
    fun `test if name having number expectancy True`() {
        val verify = viewModel.verifyNameContainsNumber("Matheus Gusman")
        Assert.assertTrue(verify)
    }

    @Test
    fun `test if name having number expectancy False`() {
        val verify = viewModel.verifyNameContainsNumber("Matheus 3Gusman")
        Assert.assertFalse(verify)
    }


    @Test
    fun `test pattern data ptBr expectancy False`() {
        val verify = viewModel.verifyValidateDate("22/02")
        Assert.assertFalse(verify)
    }
    @Test
    fun `test pattern data number day greater than 31 expectancy False`() {
        val verify = viewModel.verifyValidateDate("33/02")
        Assert.assertFalse(verify)
    }  @Test
    fun `test pattern data negative day expectancy False`() {
        val verify = viewModel.verifyValidateDate("-01/02")
        Assert.assertFalse(verify)
    }

    @Test
    fun `test pattern data with Eua expectancy True`() {
        val verify = viewModel.verifyValidateDate("02/22")
        Assert.assertTrue(verify)
    }

    @Test
    fun `test cvv size expectancy True`() {
        val verify = viewModel.verifyNumeberCharacterCvv("123")
        Assert.assertTrue(verify)
    }

    @Test
    fun `test cvv size with less character expectancy False`() {
        val verify = viewModel.verifyNumeberCharacterCvv("12")
        Assert.assertFalse(verify)
    }

    @Test
    fun `test cvv size with more character expectancy False`() {
        val verify = viewModel.verifyNumeberCharacterCvv("1234")
        Assert.assertFalse(verify)
    }

}
