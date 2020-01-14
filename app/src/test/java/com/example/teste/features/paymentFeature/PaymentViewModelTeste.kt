package com.example.teste.features.paymentFeature


import com.example.teste.InstantExecutorExtension
import com.example.teste.data.Repository
import com.example.teste.data.model.CreditCard
import com.example.teste.data.model.User
import io.mockk.mockk
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantExecutorExtension::class)
internal class PaymentViewModelTeste {
    private val repository = mockk<Repository>(relaxed = true)
    private lateinit var viewModel: PaymentViewModel
    @BeforeEach
    fun setup() {
        viewModel = PaymentViewModel(repository)
    }

    @Test
    fun `test if set data in creditCard passing something`() {
        val creditCard = CreditCard(1, "", "", "", "")
        viewModel.setupCreditCard(creditCard)
        assertThat(viewModel.creditCard.value).isNotNull
    }

    @Test
    fun `test if creditCard start null`() {
        assertThat(viewModel.creditCard.value).isNull()
    }

    @Test
    fun `verify init values with zero waiting true`() {
        assertThat(viewModel._valuePayment.value).isEqualTo("0.00")
    }


    @Test
    fun `verify init values with zero waiting false`() {
        assertThat(viewModel._valuePayment.value).isNotEqualTo("1.00")
    }

    @Test
    fun `verify if have creditCard waiting true`() {

    }

    @Test
    fun `verify if have creditCard waiting false`() {
        val haveCard = viewModel.verifyHasCard()
        assertThat(haveCard).isEqualTo(viewModel.creditCard.value != null)
    }

    @Test
    fun `verify setUser if set value waiting true`() {
        val user = User(1, "", "", "")
        viewModel.setUser(user)
        assertThat(viewModel.user.value).isNotNull
    }

    @Test
    fun `verify setUser if set value waiting false`() {
        assertThat(viewModel.user.value).isNull()
    }

    @Test
    fun `verify create receipt`() {
        val receipt = viewModel.createReceipt()
        assertThat(receipt).isNotNull
    }
}
