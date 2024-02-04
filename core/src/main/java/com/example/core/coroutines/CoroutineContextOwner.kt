package com.example.core.coroutines

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

interface CoroutineContextOwner {
    val mainCoroutineContext: CoroutineContext
    val ioCoroutineContext: CoroutineContext
    val defaultCoroutineContext: CoroutineContext
}

internal class StandardCoroutineContexts(
    private val exceptionHandler: CoroutineExceptionHandler
) : CoroutineContextOwner {

    override val mainCoroutineContext: CoroutineContext
        get() = SupervisorJob() + Dispatchers.Main.immediate + exceptionHandler

    override val ioCoroutineContext: CoroutineContext
        get() = SupervisorJob() + Dispatchers.IO + exceptionHandler

    override val defaultCoroutineContext: CoroutineContext
        get() = SupervisorJob() + Dispatchers.Default + exceptionHandler

}
