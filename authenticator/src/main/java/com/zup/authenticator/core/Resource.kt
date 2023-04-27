package com.zup.authenticator.core

sealed class Resource<T>(val data: T? = null, val error: Throwable? = null) {
    class Initialize<T> : Resource<T>()
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(error: Throwable?, data: T? = null) : Resource<T>(data, error)
    class Loading<T>(data: T? = null) : Resource<T>(data)
}