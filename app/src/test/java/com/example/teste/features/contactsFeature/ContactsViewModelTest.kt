package com.example.teste.features.contactsFeature

import com.example.teste.adapter.AdapterRC
import com.example.teste.data.Repository
import com.example.teste.data.model.User
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verifyOrder
import org.junit.Assert
import org.junit.Test
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.extension.ExtendWith


@ExtendWith(MockKExtension::class)
internal class ContactsViewModelTest {

    private val repository = mockk<Repository>(relaxed = true)
    private val viewModel:ContactsViewModel = ContactsViewModel(repository)
    private val adapter: AdapterRC = mockk(relaxed = true)

    @BeforeAll
    fun setup(){
        clearAllMocks()
    }
    @Test
    fun `test request`(){
        every { viewModel.requestUsers() } answers {}
        viewModel.requestUsers()
        viewModel.repository.getUser(viewModel.listUser)
        viewModel.repository.remoteDataSource.requestListUser(viewModel.listUser)
        verifyOrder {
            viewModel.requestUsers()
            viewModel.repository.getUser(viewModel.listUser)
            viewModel.repository.remoteDataSource.requestListUser(viewModel.listUser)
        }
    }
    @Test
    fun `test filter passing user and filter rigth`(){
        val charsSearched = "eduardo"
        adapter.setValues(mutableListOf(User(1,"eduardo","","eduardo.silva")))
        val listFilted = adapter.filterUser(charsSearched)
        Assert.assertTrue(listFilted.isNotEmpty())

    }
    @Test
    fun `test filter passing wrong user`(){
        val charsSearched = "carlos"
        adapter.setValues(mutableListOf(User(1,"eduardo","","eduardo.silva")))
        val listFilted = adapter.filterUser(charsSearched)
        Assert.assertFalse(listFilted.isEmpty())

    }
    @Test
    fun `test filter not passing user`(){
        val charsSearched = "eduardo"
        val listFilted = adapter.filterUser(charsSearched)
        Assert.assertFalse(listFilted.isEmpty())

    }
}