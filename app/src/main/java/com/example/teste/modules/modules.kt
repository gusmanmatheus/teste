package com.example.teste.modules

import com.example.teste.contacts.ContactsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val contactsModule = module {
    viewModel { ContactsViewModel() }
}