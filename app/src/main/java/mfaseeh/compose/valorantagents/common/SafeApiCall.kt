package mfaseeh.compose.valorantagents.common

import io.ktor.client.plugins.*
import mfaseeh.compose.valorantagents.common.exception.HttpException
import mfaseeh.compose.valorantagents.common.exception.NoInternetException
import java.io.IOException


internal suspend fun <T> safeApiCall(apiCall: suspend () -> T): ResultState<T> {
    return try {
        val response = apiCall.invoke()
        ResultState.Success(response)
    } catch (throwable: Throwable) {
        when (throwable) {
            is IOException -> ResultState.Error(NoInternetException())
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
