package com.example.teste.modules

import androidx.databinding.DataBindingUtil.setContentView
import com.example.teste.contactsFeature.ContactsActivity
import com.example.teste.contactsFeature.ContactsViewModel
import com.example.teste.data.Repository
import com.example.teste.data.remote.RemoteDataSource
import com.example.teste.databinding.ActivityContactsBinding
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module
import org.mockito.Mockito.mock

val contactsModule = module {
    factory {
        RemoteDataSource()
    } bind RemoteDataSource::class

    factory {
        Repository(get())
    } bind Repository::class

    viewModel { ContactsViewModel(get()) }
    single { (activity: ContactsActivity, layoutId: Int) ->
        setContentView(
            activity,
            layoutId
        ) as ActivityContactsBinding
    } bind ActivityContactsBinding::class
}


