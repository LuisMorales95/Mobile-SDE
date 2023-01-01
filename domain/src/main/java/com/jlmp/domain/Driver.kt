package com.jlmp.domain

import com.jlmp.common.utils.commonFactors
import com.jlmp.common.utils.consonantCount
import com.jlmp.common.utils.vowelCount

data class Driver(
    var id: Long = 0L,
    var name: String = "",
    var idShipment: Long = 0L
) {

    private fun evenScore(): Double {
        return name.vowelCount() * 1.5
    }

    private fun oddScore(): Double {
        return name.consonantCount() * 1.0
    }

    fun getScore(isEven: Boolean) = if (isEven) evenScore() else oddScore()
}

fun List<Driver>.highestScore(isEven: Boolean, addressLength: Int): Driver {
    return this.maxBy {
//        val cf = mutableListOf<Int>()
        var score = it.getScore(isEven)

        val commonFactors = commonFactors(it.name.length, addressLength)
//        val dcf = it.name.length.commonFactors()
//        commonFactors.onEach { n -> if (dcf.contains(n)) cf.add(n) }
        if (commonFactors.size > 1) { score += (score * .5) }
        score
    }
}