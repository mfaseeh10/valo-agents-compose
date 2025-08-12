package mfaseeh.compose.valorantagents.common

sealed interface ResultState<out T> {
    data class Success<T>(val data: T) : ResultState<T>
    data class Error(val exception: Throwable? = null) : ResultState<Nothing>
}

fun ResultState.Error.getErrorMessage() = this.exception?.message.orEmpty()