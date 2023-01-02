package com.jlmp.mobilesdecodeexercise.core.database

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.jlmp.domain.Driver
import com.jlmp.domain.Shipment
import com.jlmp.mobilesdecodeexercise.MainCoroutineRule
import com.jlmp.mobilesdecodeexercise.core.database.embedded.EmbeddedDriverWithShipment
import com.jlmp.mobilesdecodeexercise.core.database.mapper.toEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@MediumTest
class DriverDaoTest {

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
    fun insertDriver() = runBlockingTest {
        val driver = Driver(id = 1L, name = "John Doe")
        database.driverDao().insert(driver.toEntity())
        val loadedDriver = database.driverDao().getById(driver.id)
        assertThat(driver.id, IsEqual(loadedDriver.id))
        assertThat(driver.name, IsEqual(loadedDriver.name))
    }

    @Test
    fun updateDriver() = runBlockingTest {
        val driver = Driver(id = 1L, name = "John Doe")
        database.driverDao().insert(driver.toEntity())
        val loadedDriverEntity = database.driverDao().getById(driver.id)
        loadedDriverEntity.idShipment = 1L

        database.driverDao().update(loadedDriverEntity)

        val updatedDriverEntity = database.driverDao().getById(driver.id)

        assertThat(loadedDriverEntity.id, IsEqual(updatedDriverEntity.id))
        assertThat(loadedDriverEntity.name, IsEqual(updatedDriverEntity.name))
    }

    @Test
    fun getEmbeddedDriverWithShipment() = runBlockingTest {

        val shipment = Shipment(id = 1L, address = "1001 Unknown Street")
        val driver = Driver(id = 1L, name = "John Doe", idShipment = shipment.id)
        database.driverDao().insert(driver.toEntity())
        database.shipmentDao().insert(shipment.toEntity())

        var embeddedDriverWithShipment: EmbeddedDriverWithShipment? = null
        val latch = CountDownLatch(1)
        val job = launch(Dispatchers.IO) {
            database.driverDao().getAllDriverWithShipmentFlow().collect {
                embeddedDriverWithShipment = it.first()
                latch.countDown()
            }
        }
        latch.await()
        job.cancel()

        embeddedDriverWithShipment?.let {
            assertThat(driver.id, IsEqual(it.driverEntity.id))
            assertThat(driver.name, IsEqual(it.driverEntity.name))
            assertThat(driver.idShipment, IsEqual(it.driverEntity.idShipment))
            assertThat(shipment.id, IsEqual(it.shipmentEntity?.id))
            assertThat(shipment.address, IsEqual(it.shipmentEntity?.address))
        }

    }
}