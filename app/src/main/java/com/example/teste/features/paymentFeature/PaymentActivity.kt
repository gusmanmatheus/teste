package com.example.teste.features.paymentFeature

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
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
        setToolbar()
        recoveryData()
        showNumberCard()
        setObservables()
    }

    private fun setObservables() {
        observableValue()
        observablePoint()
    }

    private fun observableValue() {
        paymentViewModel.valuePayment.observe(this, Observer {
            if (it != "") {
                val value = it.replace(",", ".").replace(".", "").toDouble()
                changeColorTextValuePayment(value > 0.0)
            }
        })
    }

    private fun observablePoint() {
        valuePayment.changeText {
            if (maskPayControl) {
                maskPayControl = false
                paymentViewModel._valuePayment.value = Utils.maskValue(binding.valuePayment)
                binding.valuePayment.setSelection(binding.valuePayment.text.length - 1)
            } else {
                maskPayControl = true
            }
            binding.valuePayment.setSelection(binding.valuePayment.text.length)

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

    private fun showNumberCard() {
        var ballValue =
            resources.getString(R.string.masterCard) + " " + resources.getString(R.string.ball)
        ballValue += " " + paymentViewModel.creditCard.value?.numberCard?.substring(0, 3)
        masterCard.text = ballValue
    }

    private fun recoveryData() {
        recoveryCreditCard()
        recoveryUser()
    }

    private fun recoveryUser() {
        val user = intent.getSerializableExtra(resources.getString(R.string.UserPayment)) as User
        paymentViewModel.setUser(user)
    }

    private fun recoveryCreditCard() {
        val creditCard =
            intent.getSerializableExtra(resources.getString(R.string.cardPayment)) as CreditCard
        paymentViewModel.setCreditCard(creditCard)
    }

    private fun setToolbar() {
        setSupportActionBar(toolbarPayment)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.title = ""
        supportActionBar?.setHomeAsUpIndicator(R.drawable.back_left_48dp)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home
            -> {
                onBackPressed()
            }
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }


}
