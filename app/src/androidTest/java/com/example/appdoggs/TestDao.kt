package com.example.appdoggs

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.appdoggs.model.local.Dao.DogsDao
import com.example.appdoggs.model.local.DogsDatabase
import com.example.appdoggs.model.local.Entities.Razas
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestDao {
    private lateinit var dDao: DogsDao
    private lateinit var dDB: DogsDatabase


    @Before
    fun setup(){
        val context= ApplicationProvider.getApplicationContext<android.content.Context>()
        dDB=  Room.inMemoryDatabaseBuilder(context,DogsDatabase::class.java).build()
        dDao=dDB.dogsDao()
    }
    @After
    fun shutdown(){
        dDB.close()
    }

    @Test
    fun testInsertRazas() = runBlocking  {
        val RazasList= listOf(
            Razas("Perro1" ),
            Razas("Perro2" ),
            Razas("Perro3" ),
            Razas("Perro4" )
        )
        dDao.insertAllBreedList(RazasList)

        val razasLiveData = dDao.getAllBreedList()
        val listRazas = razasLiveData.value?: emptyList()

        MatcherAssert.assertThat(listRazas, CoreMatchers.not(emptyList()))
        MatcherAssert.assertThat(listRazas.size, CoreMatchers.equalTo(4))

    }

}