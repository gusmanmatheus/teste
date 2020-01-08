package com.example.teste.features.paymentFeature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.teste.R
import com.example.teste.data.model.CreditCard
import com.example.teste.data.model.User
import com.example.teste.databinding.ActivityPaymentBinding
import com.example.teste.modules.Utils
import com.example.teste.modules.changeText
import kotlinx.android.synthetic.main.activity_payment.*
import org.koin.android.ext.android.inject

class PaymentActivity : AppCompatActivity() {
    private val paymentViewModel by inject<PaymentViewModel>()
    private var maskPayControl = false
    private lateinit var binding: ActivityPaymentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_payment)
        binding.lifecycleOwner = this
         binding.viewModel = this.paymentViewModel
        setObservables()
    }

    private fun setObservables() {
        observableValue()
        observablePoint()
    }

    private fun observableValue() {
        paymentViewModel.valuePayment.observe(this, Observer {
            if (it > "") {
                val value = it.replace(",",".").toDouble()
                changeColorTextValuePayment(value > 0.0)
            }
        })
    }

    private fun observablePoint() {
        valuePayment.changeText {
            if (maskPayControl) {
                maskPayControl = false
                 Utils.maskValue(binding.valuePayment)
            } else {
                maskPayControl = true
            }
        }
    }

    private fun changeColorTextValuePayment(condition: Boolean) {
        if (condition) {
            valuePayment.setTextColor(
                ResourcesCompat.getColor(
                    resources,
                    R.color.colorAccent,
                    null
                )
            )
        } else {
            valuePayment.setTextColor(
                ResourcesCompat.getColor(
                    resources,
                    R.color.grayText,
                    null
                )
            )
        }
    }

    private fun recoveryUser() {
        val user = intent.getSerializableExtra("") as User
        paymentViewModel.setUser(user)
    }

    private fun recoveryCreditCard() {
        val creditCard = intent.getSerializableExtra("") as CreditCard
        paymentViewModel.setCreditCard(creditCard)
    }


}
