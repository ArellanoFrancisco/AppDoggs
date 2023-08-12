package com.example.appdoggs

import com.example.appdoggs.model.local.Entities.Razas
import org.junit.Before
import org.junit.Test

class TestRazas {

    private lateinit var razas: Razas

    @Before
    fun setup() {
        razas = Razas(breed = "Prueba")

    }

    @Test
    fun testRazasConstructor() {
        assert(razas.breed == "Prueba")

    }
}