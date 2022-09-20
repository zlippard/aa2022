package com.zaclippard.androidaccelerator2022.utils

/**
 * Named this as [CustomResult]
 * to prevent naming clash with existing
 * built-in Kotlin [Result]
 */
sealed class CustomResult<out T> {
    data class Success<out T>(val value: T) : CustomResult<T>()
    data class Failure(val error: String): CustomResult<Nothing>()
}
