package com.noyal.demo.core

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.io.IOException

inline fun <reified R, T> safeCall(
    crossinline call: suspend () -> Response<T>,
    noinline mapper: ((T) -> R)? = null
): Flow<Result<R>> = flow {
    try {
        val response = call()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                val result = mapper?.invoke(body) ?: run {
                    if (body is R) body else throw IllegalStateException("Type mismatch")
                }
                emit(Result.Success(result))
            } else {
                emit(Result.Error("Response body is null"))
            }
        } else {
            val errorMsg = response.errorBody()?.string() ?: response.message()
            emit(Result.Error(errorMsg))
        }
    } catch (e: IOException) {
        emit(Result.Error("Network error", e))
    } catch (e: Exception) {
        emit(Result.Error("Unexpected error", e))
    }
}
