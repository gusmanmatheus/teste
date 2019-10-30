package com.example.teste.contactsFeature

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.example.teste.R
import com.example.teste.data.remote.Resources
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactsActivity : AppCompatActivity() {
    val contactsViewModel: ContactsViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)
        contactsViewModel.listUser.observe(this, Observer {
            when (it.status) {
                Resources.StatusRequest.SUCCES -> {
                    Log.i("xrl8", "viewsz" + it.data)
                }
                Resources.StatusRequest.ERROR -> {
                    Log.i("xrl8", "viewsz" + it.error)

                }
                Resources.StatusRequest.LOADING -> {
                    Log.i("xrl8", "ESPERA AI MEU CHEFE TA CARREGANDO...")
                }
            }
        })
        contactsViewModel.resp()

        contactsViewModel.sendPayment.observe(this, Observer {
            when (it.status) {
                Resources.StatusRequest.SUCCES -> {
                    Log.i("xrl8", "viewszSEND" + it.data)
                }
                Resources.StatusRequest.ERROR -> {
                    Log.i("xrl8", "viewszSEND" + it.error)

                }
                Resources.StatusRequest.LOADING -> {
                    Log.i("xrl8", "ESPERA AI MEU CHEFE TA CARREGANDO...")
                }
            }
        })
        contactsViewModel.sendpay()
    }


}
