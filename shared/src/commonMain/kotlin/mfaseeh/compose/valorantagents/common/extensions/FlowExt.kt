package mfaseeh.compose.valorantagents.common.extensions

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.launch

fun <T : Any> Flow<T?>.notNullable(): Flow<T> = transform<T?, T> { value ->
    if (value == null) {
        throw NullPointerException("data not found")
    } else {
        return@transform emit(value)
    }
}

inline fun <T : Any, U : Any> Flow<T>.collect(
    crossinline map: (value: T) -> U,
    scope: CoroutineScope,
    initialValue: U? = null,
    uiStateFlow: MutableStateFlow<U>
) {
    val flow = this
    scope.launch {
        // emit init state if not null
        initialValue?.let {
            uiStateFlow.emit(it)
        }
        // collect data stream
        flow.collect {
            uiStateFlow.emit(map.invoke(it))
        }
    }
}