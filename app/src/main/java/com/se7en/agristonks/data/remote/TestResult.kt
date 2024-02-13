package com.se7en.agristonks.data.remote

sealed class TestResult<T>(
    val data: T? = null, // for post operations
    val message: String? = null // for any errors
) {

    class Success<T> (data: T?): TestResult<T>(data)
    class Error<T> (data: T?, errorMessage: String): TestResult<T>(data, errorMessage)

}
