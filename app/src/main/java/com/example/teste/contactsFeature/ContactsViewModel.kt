package com.example.teste.contactsFeature

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.teste.data.Repository
import com.example.teste.data.model.User
import com.example.teste.data.remote.LiveResources

class ContactsViewModel( val repository: Repository) : ViewModel() {
    val listUser = LiveResources<List<User>>()
    var loading =  true

    fun requestUsers() {
        repository.getUser(listUser)
    }
}