package com.example.teste.features.contactsFeature

import com.example.teste.adapter.AdapterRC
import com.example.teste.data.model.User
import io.mockk.clearAllMocks
import io.mockk.junit5.MockKExtension
import org.junit.Assert
import org.junit.Test
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class AdapterTest {
    private val adapter: AdapterRC = AdapterRC()

    @BeforeAll
    fun setup() {
        clearAllMocks()
    }

    @Test
    fun `test filter passing user and filter rigth expectancy true`() {//vejo se 'e verdade a quantidade ser maior que 0
        val charsSearched = "eduardo"
        adapter.setValues(mutableListOf(User(1, "eduardo", "", "eduardo.silva")))
        val listFilted = adapter.filterUser(charsSearched)
        val bool: Boolean = (listFilted.count() > 0)
        Assert.assertTrue(bool)

    }

    @Test
    fun `test filter passing wrong user expectancy false`() { // vejo se 'e falso a quantidade for maior que 0
        val charsSearched = "carlos"
        adapter.setValues(mutableListOf(User(1, "eduardo", "", "eduardo.silva")))
        val listFilted = adapter.filterUser(charsSearched)
        val bool: Boolean = (listFilted.count() > 0)
        Assert.assertFalse(bool)

    }

    @Test
    fun `test filter not passing user expectancy true`() {//vejo se 'e verdade a quantidade for igual a 0
        val charsSearched = "eduardo"
        val listFilted = adapter.filterUser(charsSearched)
        val bool: Boolean = (listFilted.count() == 0)
        Assert.assertTrue(bool)

    }
}