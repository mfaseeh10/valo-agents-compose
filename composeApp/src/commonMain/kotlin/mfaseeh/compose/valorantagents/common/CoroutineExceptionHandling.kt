package mfaseeh.compose.valorantagents.common

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

/**
 * Global exception handler for coroutines
 * This prevents unhandled exceptions from crashing the app on iOS
 */
val globalExceptionHandler = CoroutineExceptionHandler { context, exception ->
    println("CoroutineExceptionHandler got $exception in context $context")
    // Log the exception (in a real app, you'd send this to your crash reporting service)
    exception.printStackTrace()
}

/**
 * Safe coroutine scope with proper exception handling
 * Use this for any background operations that might fail
 */
val safeCoroutineScope = CoroutineScope(
    SupervisorJob() + 
    Dispatchers.Main + 
    globalExceptionHandler + 
    CoroutineName("SafeScope")
)

/**
 * Extension function to safely launch coroutines with exception handling
 */
fun CoroutineScope.safeLaunch(
    exceptionHandler: CoroutineExceptionHandler = globalExceptionHandler,
    block: suspend CoroutineScope.() -> Unit
) {
    launch(context = exceptionHandler, block = block)
}
