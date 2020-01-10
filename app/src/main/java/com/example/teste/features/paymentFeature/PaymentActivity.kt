package com.example.teste.features.paymentFeature

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.teste.R
import com.example.teste.data.model.CreditCard
import com.example.teste.data.model.User
import com.example.teste.data.remote.Resources
import com.example.teste.databinding.ActivityPaymentBinding
import com.example.teste.features.receiptFeature.ReceiptPayment
import com.example.teste.features.registerCard.RegisterCardActivity
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
        setClicks()
        makePayment()
        nextActivity()
    }

    companion object {
        private const val EXTRA_USER = "EXTRA_USER"
        private fun newIntent(context: Context, user: User): Intent {
            return Intent(context, PaymentActivity::class.java).apply {
                putExtra(EXTRA_USER, user)
            }
        }

        fun startActivity(activity: Activity, user: User) {
            activity.startActivity(newIntent(activity, user))

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when {
            RegisterCardActivity.isOrigin(requestCode) -> {
                val creditCard = RegisterCardActivity.getCreditCard(resultCode, data)
                creditCard?.let {
                    binding.viewModel?.setupCreditCard(it)
                    showNumberCard()
                }
                if (paymentViewModel.creditCard.value == null) {
                    finish()
                }
            }
            else -> super.onActivityResult(requestCode, resultCode, data)

        }

    }

    private fun nextActivity() {
        if (!paymentViewModel.verifyHasCard()) {
            paymentViewModel.user.value?.let {
                RegisterCardActivity.startActivityForResult(this, it, null)
            }
        }
    }

    private fun setObservables() {
        observableValue()
        observablePoint()
        observePaymentResult()

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

    private fun observePaymentResult() {
        paymentViewModel.paymentResult.observe(this, Observer {
            it?.let {
                when (it.status) {
                    Resources.StatusRequest.SUCCES -> {
                        paymentButton.isClickable = true
                        it.data?.let { data ->
                            val receipt = ReceiptPayment( )
                            receipt.show(supportFragmentManager, receipt.tag)
                            Log.i("xrl8", data.toString() + "XD")
                        }
                    }
                    Resources.StatusRequest.ERROR -> {
                        paymentButton.isClickable = true
                        it.error?.let { error ->
                            Log.i("xrl8", error.toString() + "XD")
                        }

                    }
                    Resources.StatusRequest.LOADING -> {
                        paymentButton.isClickable = false
                    }
                }
            }
        })
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

    private fun makePayment() {
        paymentButton.setOnClickListener {
            paymentViewModel.sendPayment()
        }
    }

    private fun showNumberCard() {
        val lengthNumerCard = paymentViewModel.creditCard.value?.numberCard?.length ?: 16
        var ballValue =
            resources.getString(R.string.masterCard) + " " + resources.getString(R.string.ball)
        ballValue += " " + paymentViewModel.creditCard.value?.numberCard?.substring(
            lengthNumerCard - 4,
            lengthNumerCard - 1
        )
        masterCard.text = ballValue
    }

    private fun recoveryData() {
        recoveryCreditCard()
        recoveryUser()
    }

    private fun recoveryUser() {
        val user = intent.getSerializableExtra(EXTRA_USER) as User
        paymentViewModel.setUser(user)
    }

    private fun recoveryCreditCard() {
        paymentViewModel.setupCreditCard()
    }

    private fun setClicks() {
        editCardClick()
    }

    private fun editCardClick() {
        editCard.setOnClickListener {
            val creditCard = paymentViewModel.creditCard.value
            paymentViewModel.user.value?.let {
                RegisterCardActivity.startActivityForResult(this, it, creditCard)
            }
        }
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
