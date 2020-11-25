package dispatcher

import kotlinx.coroutines.CoroutineDispatcher

class DefaultDispatcherProvider(
    override val default: CoroutineDispatcher,
    override val io: CoroutineDispatcher,
) : DispatcherProvider