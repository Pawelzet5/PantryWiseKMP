package org.example.pantrywisecmp.core.domain.extensions

inline fun <T> List<T>.updateFirst(
    predicate: (T) -> Boolean,
    transform: (T) -> T
): List<T> =
    map { item ->
        if (predicate(item)) transform(item) else item
    }