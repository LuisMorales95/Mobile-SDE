package com.jlmp.mobilesdecodeexercise.core.database

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.jlmp.domain.Shipment
import com.jlmp.mobilesdecodeexercise.core.database.mapper.toEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsEqual
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@MediumTest
class ShipmentDaoTest {


    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }

    @After
    fun closeDatabase() {
        database.close()
    }

    @Test
    fun insertAddress() = runBlockingTest {
        val shipment = Shipment(id = 1L, address = "1001 Unknown Street")
        database.shipmentDao().insert(shipment.toEntity())
        val loadedShipment = database.shipmentDao().get(shipment.id)
        MatcherAssert.assertThat(shipment.id, IsEqual(loadedShipment.id))
        MatcherAssert.assertThat(shipment.address, IsEqual(loadedShipment.address))
    }
}