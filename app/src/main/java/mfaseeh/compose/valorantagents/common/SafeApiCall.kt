package mfaseeh.compose.valorantagents.common

import mfaseeh.compose.valorantagents.common.exception.HttpException
import retrofit2.HttpException as RetrofitException
import mfaseeh.compose.valorantagents.common.exception.NoInternetException
import java.io.IOException


internal suspend fun <T> safeApiCall(apiCall: suspend () -> T): ResultState<T> {
    return try {
        val response = apiCall.invoke()
        ResultState.Success(response)
    } catch (throwable: Throwable) {
        when (throwable) {
            is IOException -> ResultState.Error(NoInternetException())
            is RetrofitException -> ResultState.Error(
                HttpException(
                    errorCode = throwable.code(),
                    errorMessage = throwable.message() ?: ""
                )
            )
            else -> ResultState.Error(throwable)
        }
    }
}
