package core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

data class Owner(val reference: Any) : CoroutineContext.Element {
    override val key: CoroutineContext.Key<*> get() = Key

    companion object Key : CoroutineContext.Key<Owner>
}

suspend fun <R> Any.ownerCoroutineScope(block: suspend CoroutineScope.() -> R): R {
    return withContext(coroutineContext + Owner(this), block)
}