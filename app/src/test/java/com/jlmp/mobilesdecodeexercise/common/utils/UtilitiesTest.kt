package com.jlmp.mobilesdecodeexercise.common.utils

import com.jlmp.common.utils.commonFactors
import com.jlmp.common.utils.consonantCount
import com.jlmp.common.utils.vowelCount
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Test

class UtilitiesTest {

    private val driver = "Howard Emmerich"
    private val driveFactors = listOf(1, 3, 5, 15)
    private val address = "63187 Volkman Garden Suite 447"
    private val addressFactors = listOf(1, 2, 3, 5, 6, 10, 15, 30)
    private val commonFactors = listOf(1, 3, 5, 15)

    @Test
    fun getConsonantCount() {
        val count = driver.consonantCount()
        assertThat(9, IsEqual(count))
    }

    @Test
    fun getVowelCount() {
        val count = driver.vowelCount()
        assertThat(5, IsEqual(count))
    }

    @Test
    fun getCommonFactors_driverFactors() {
        val driverLength = driver.length
        val factors = driverLength.commonFactors()
        assertThat(this.driveFactors, IsEqual(factors))
    }

    @Test
    fun getCommonFactors_addressFactors() {
        val addressLength = address.length
        val factors = addressLength.commonFactors()
        assertThat(this.addressFactors, IsEqual(factors))
    }

    @Test
    fun getCommonFactors_driverAndAddress() {
        val driverLength = driver.length
        val addressLength = address.length
        val commonFactors = commonFactors(driverLength, addressLength)
        assertThat(this.commonFactors, IsEqual(commonFactors))
    }
}