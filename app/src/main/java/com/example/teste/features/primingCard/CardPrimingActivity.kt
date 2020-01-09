package com.example.teste.features.primingCard

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.teste.R
import com.example.teste.data.model.User
import com.example.teste.features.registerCard.RegisterCardActivity
import kotlinx.android.synthetic.main.activity_card_priming.*

class CardPrimingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_priming)
        setToolbar()
        recoveryData()
    }

    private fun recoveryData() {
        val user = intent.getSerializableExtra(resources.getString(R.string.UserPayment)) as User
        Toast.makeText(this, user.name, Toast.LENGTH_SHORT).show()
        val intent = Intent(this, RegisterCardActivity::class.java)
        intent.putExtra(resources.getString(R.string.UserPayment), user)
        startActivity(intent)
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
