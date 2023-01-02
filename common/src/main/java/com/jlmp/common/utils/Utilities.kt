package com.jlmp.common.utils




val vowels = listOf('a','e','i', 'o', 'u')

fun String.removeSpace(): String {
    return this.trim().replace(" ","")
}

fun String.vowelCount(): Int {
    return this.removeSpace().lowercase().toCharArray().filter { vowels.contains(it) }.size
}

fun String.consonantCount(): Int {
    return this.removeSpace().lowercase().toCharArray().filter { !vowels.contains(it) }.size
}

fun Int.commonFactors(): List<Int> {
    val factors = mutableListOf<Int>()
    (1 .. this).forEach {
        if ((this % it) == 0) factors.add(it)
    }
    return factors
}

fun commonFactors(num1: Int, num2: Int): List<Int> {
    val factor1 = mutableListOf<Int>()
    val factor2 = mutableListOf<Int>()
    (1 .. num1).forEach {
        if ((num1 % it) == 0) factor1.add(it)
    }
    (1 .. num2).forEach {
        if ((num2 % it) == 0) factor2.add(it)
    }
    return factor1.intersect(factor2.toSet()).toList()
}
