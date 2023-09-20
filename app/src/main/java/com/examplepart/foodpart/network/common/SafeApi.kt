package com.examplepart.foodpart.network.common


import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.isActive
import retrofit2.Response
import java.io.IOException

suspend fun <T> safeApi(
    call: suspend () -> Response<T>,
    onDataReady: (T) -> Unit
): Flow<Result> {
    return flow {
        emit(Result.Loading)

        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    if (currentCoroutineContext().isActive) {
                        onDataReady(body)
                    }
                    emit(Result.Success)
                } else {
                    emit(Result.Error("whoops body was empty"))
                }
            } else {
                emit(Result.Error("whoops: got ${response.code()} code!"))
            }
        } catch (e: IOException) {
            emit(Result.Error("Internet"))
        } catch (e: Exception) {
            emit(Result.Error("Report"))
        }
    }.cancellable()
}