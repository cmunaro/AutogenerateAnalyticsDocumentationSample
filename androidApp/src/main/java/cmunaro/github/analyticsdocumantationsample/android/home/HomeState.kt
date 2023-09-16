package cmunaro.github.analyticsdocumantationsample.android.home

import androidx.compose.runtime.Immutable
import cmunaro.github.analyticsdocumantationsample.android.utils.Async

@Immutable
data class HomeState(
    val accountBalance: Async<Double> = Async.Uninitialized
)
