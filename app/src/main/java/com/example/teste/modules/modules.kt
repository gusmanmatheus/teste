package com.example.teste.modules

import androidx.databinding.DataBindingUtil.setContentView
import com.example.teste.adapter.AdapterRC
import com.example.teste.features.contactsFeature.ContactsActivity
import com.example.teste.features.contactsFeature.ContactsViewModel
import com.example.teste.data.Repository
import com.example.teste.data.model.User
import com.example.teste.data.remote.RemoteDataSource
import com.example.teste.databinding.ActivityContactsBinding
import com.example.teste.features.paymentFeature.PaymentViewModel
import com.example.teste.features.registerCard.RegisterCardViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

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

    factory {
        AdapterRC()
    }

}
val registerCardModule = module {
    viewModel { RegisterCardViewModel() }
}

val paymentModule = module {
    viewModel { PaymentViewModel() }
}

