package core

sealed class Resource<out T, out R> {
    data class Loaded<T>(val value: T) : Resource<T, Nothing>()
    data class Error<T>(val value: T) : Resource<Nothing, T>()

    inline val get get() = (this as? Loaded<T>)?.value ?: (this as Error<R>).value
}

inline fun <T> safeResource(provider: () -> T) =
    runCatching(provider)
        .map { Resource.Loaded(it) }
        .getOrElse { Resource.Error(it) }