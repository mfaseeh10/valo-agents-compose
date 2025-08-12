package mfaseeh.compose.valorantagents.common

import io.ktor.client.plugins.*
import mfaseeh.compose.valorantagents.common.exception.HttpException
import mfaseeh.compose.valorantagents.common.exception.NoInternetException


suspend fun <T> safeApiCall(apiCall: suspend () -> T): ResultState<T> {
    return try {
        val response = apiCall.invoke()
        ResultState.Success(response)
    } catch (throwable: Throwable) {
        when (throwable) {
            is Exception -> {
                if (throwable.message?.contains("network", ignoreCase = true) == true) {
                    ResultState.Error(NoInternetException())
                } else {
                    ResultState.Error(throwable)
                }
            }
            is ResponseException -> ResultState.Error(
                HttpException(
                    errorCode = throwable.response.status.value,
                    errorMessage = throwable.message ?: ""
                )
            )
            else -> ResultState.Error(throwable)
        }
    }
}