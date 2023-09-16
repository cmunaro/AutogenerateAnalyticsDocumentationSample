package cmunaro.github.analyticsdocumantationsample.android.utils

sealed interface Async<out T> {
    val value: T?
        get() = null

    data object Uninitialized : Async<Nothing>

    data class Loading<T>(override val value: T? = null) : Async<T>

    data class Success<T>(override val value: T) : Async<T>

    data class Fail<T>(val throwable: Throwable, override val value: T? = null) : Async<T>
}
