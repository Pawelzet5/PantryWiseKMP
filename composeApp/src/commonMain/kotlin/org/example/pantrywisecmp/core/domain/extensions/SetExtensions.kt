package org.example.pantrywisecmp.core.domain.extensions

fun <T> Set<T>.toggle(value: T): Set<T> =
    if (this.contains(value))
        this.minus(value)
    else this.plus(value)
