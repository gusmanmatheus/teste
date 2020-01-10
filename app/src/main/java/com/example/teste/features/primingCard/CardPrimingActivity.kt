package com.example.teste.features.primingCard

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.teste.R
import com.example.teste.data.model.User
import com.example.teste.features.paymentFeature.CardPrimingViewModel
import com.example.teste.features.registerCard.RegisterCardActivity
import kotlinx.android.synthetic.main.view_priming_card.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CardPrimingActivity : AppCompatActivity() {
        private val viewModelPriming: CardPrimingViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_priming_card)
        setToolbar()
        recoveryData()
        clickButton()
    }

    private fun clickButton() {
        registerACard.setOnClickListener {
            nextActivity()
        }
    }
    private fun recoveryData() {
        val user = intent.getSerializableExtra(resources.getString(R.string.UserPayment)) as User
        viewModelPriming.setupUser(user)
        Toast.makeText(this, user.name, Toast.LENGTH_SHORT).show()
    }

    private fun nextActivity() {
        RegisterCardActivity.startActivityForResult(this,viewModelPriming.user,null)

//        val intent = Intent(this, RegisterCardActivity::class.java)
//        intent.putExtra(resources.getString(R.string.UserPayment), viewModelPriming.user)
//        startActivity(intent)
    }

    private fun setToolbar() {
        setSupportActionBar(toolbar)
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
