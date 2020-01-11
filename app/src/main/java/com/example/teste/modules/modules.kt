package com.example.teste.modules

import androidx.room.Room
import com.example.teste.adapter.AdapterRC
import com.example.teste.features.contactsFeature.ContactsViewModel
import com.example.teste.data.Repository
import com.example.teste.data.dao.AppDataBase
import com.example.teste.data.remote.RemoteDataSource
import com.example.teste.features.paymentFeature.PaymentViewModel
import com.example.teste.features.registerCard.RegisterCardViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val contactsModule = module {
    viewModel { ContactsViewModel(get()) }
    factory {
        AdapterRC()
    }
}
val repositoryData = module {
    factory {
        RemoteDataSource()
    } bind RemoteDataSource::class
    factory {
        Repository(get(), get())
    } bind Repository::class

    single {
        Room.databaseBuilder(
            get(), AppDataBase::class.java,
            "my-db"
        ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
}

val registerCardModule = module {
    viewModel { RegisterCardViewModel(get()) }
}

val paymentModule = module {
    viewModel { PaymentViewModel(get()) }
}


