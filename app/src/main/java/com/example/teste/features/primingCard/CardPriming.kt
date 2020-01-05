package com.example.teste.features.primingCard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.teste.R
import com.example.teste.data.model.User
import com.example.teste.features.registerCard.RegisterCardActivity

class CardPriming : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_priming)
        recoveryData()
    }

    private fun recoveryData() {
        val user = intent.getSerializableExtra(resources.getString(R.string.UserPayment)) as User
        Toast.makeText(this, user.name, Toast.LENGTH_SHORT).show()
        val intent = Intent(this, RegisterCardActivity::class.java)
        startActivity(intent)
    }
}
