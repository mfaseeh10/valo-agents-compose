package mfaseeh.compose.valorantagents.common.extensions

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.launch
import mfaseeh.compose.valorantagents.common.exception.CustomException
import mfaseeh.compose.valorantagents.common.globalExceptionHandler

fun <T : Any> Flow<T?>.notNullable(): Flow<T> = transform<T?, T> { value ->
    if (value == null) {
        // Instead of throwing directly, emit an error through the flow
        throw CustomException("Data not found - null value encountered")
    } else {
        return@transform emit(value)
    }
}.catch { exception ->
    // Catch any exceptions and handle them gracefully
    println("Flow error in notNullable: $exception")
    throw exception
}

inline fun <T : Any, U : Any> Flow<T>.collect(
    crossinline map: (value: T) -> U,
    scope: CoroutineScope,
    initialValue: U? = null,
    uiStateFlow: MutableStateFlow<U>
) {
    val flow = this
    scope.launch(globalExceptionHandler) {
        try {
            // emit init state if not null
            initialValue?.let {
                uiStateFlow.emit(it)
            }
            // collect data stream with exception handling
            flow.catch { exception ->
                println("Flow collection error: $exception")
                // Don't re-throw, just log and continue
            }.collect {
                uiStateFlow.emit(map.invoke(it))
            }
        } catch (exception: Exception) {
            println("Exception in flow collection: $exception")
            // Handle the exception gracefully without crashing
        }
    }
}