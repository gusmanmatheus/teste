package com.example.teste.contacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.teste.R
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactsActivity : AppCompatActivity() {
    val contactsViewModel: ContactsViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)
    }


}
