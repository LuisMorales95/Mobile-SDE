package com.jlmp.interactors

abstract class BaseCase<in T,out R> {
    abstract suspend operator fun invoke(data: T) : R
}
