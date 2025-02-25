package com.test.domain.utils

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

inline fun <reified T> Moshi.toJson(data: T): String {
    return this.adapter(T::class.java).toJson(data)
}

inline fun <reified T> Moshi.toJsonList(data: List<T>): String {
    val type = Types.newParameterizedType(List::class.java, T::class.java)
    return this.adapter<List<T>>(type).toJson(data)
}