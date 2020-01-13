package com.example.teste.features.RegisterCardFeature

import com.example.teste.InstantExecutorExtension
import com.example.teste.data.Repository
import com.example.teste.features.registerCard.RegisterCardViewModel
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith


@ExtendWith(InstantExecutorExtension::class)
class RegisterCardViewModelTest {
    private val repository: Repository = mockk(relaxed = true)
    private lateinit var viewModel: RegisterCardViewModel


    @BeforeEach
    fun setup() {
       viewModel = RegisterCardViewModel(repository)
        viewModel.card.observeForever { }
     }

    @Test
    fun `test numberCard size expectancy True`() {
        val verify = viewModel.verifyNumeberCharacterNumberCard("0123456789000000")//16
        assertThat(verify).isTrue()
    }

    @Test
    fun `test numberCard size with less character expectancy False`() {
        val verify = viewModel.verifyNumeberCharacterNumberCard("0123456789")//10
        assertThat(verify).isFalse()
    }

    @Test
    fun `test numberCard size with more character expectancy False`() {
        val verify = viewModel.verifyNumeberCharacterNumberCard("012345678900000000")//17
        assertThat(verify).isFalse()

    }

    @Test
    fun `test if name having number expectancy True`() {
        val verify = viewModel.verifyNameContainsNumber("Matheus Gusman")
        assertThat(verify).isEqualTo(true)
    }

    @Test
    fun `test if name having number expectancy False`() {
        val verify = viewModel.verifyNameContainsNumber("Matheus 3Gusman")
        assertThat(verify).isFalse()
    }

    @Test
    fun `test pattern data ptBr expectancy False`() {
        val verify = viewModel.verifyValidateDate("22/02")
        assertThat(verify).isFalse()
    }

    @Test
    fun `test pattern data number day greater than 31 expectancy False`() {
        val verify = viewModel.verifyValidateDate("33/02")
        assertThat(verify).isFalse()
    }

    @Test
    fun `test pattern data negative day expectancy False`() {
        val verify = viewModel.verifyValidateDate("-01/02")
        assertThat(verify).isFalse()
    }

    @Test
    fun `test pattern data with Eua expectancy True`() {
        val verify = viewModel.verifyValidateDate("02/22")
        assertThat(verify).isTrue()
    }

    @Test
    fun `test cvv size expectancy True`() {
        val verify = viewModel.verifyNumeberCharacterCvv("123")
        assertThat(verify).isTrue()
    }

    @Test
    fun `test cvv size with less character expectancy False`() {
        val verify = viewModel.verifyNumeberCharacterCvv("12")
        assertThat(verify).isFalse()
    }

    @Test
    fun `test cvv size with more character expectancy False`() {
        val verify = viewModel.verifyNumeberCharacterCvv("1234")
        assertThat(verify).isFalse()
    }
}
