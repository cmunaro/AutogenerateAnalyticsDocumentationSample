package cmunaro.github.analyticsdocumantationsample.android.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cmunaro.github.analyticsdocumantationsample.analytics.domain.AnalyticsRepository
import cmunaro.github.analyticsdocumantationsample.android.utils.Async
import cmunaro.github.analyticsdocumantationsample.bank.domain.BankRepository
import cmunaro.github.analyticsdocumantationsample.events.BalanceDecreasedEvent
import cmunaro.github.analyticsdocumantationsample.events.BalanceIncreasedEvent
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(
    private val bankRepository: BankRepository,
    private val analyticsRepository: AnalyticsRepository
) : ViewModel() {
    val state = bankRepository.balance.map {
        HomeState(accountBalance = Async.Success(it))
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = HomeState()
    )

    fun onAdd1000Dollars() {
        runCatching {
            bankRepository.addMoney(1000.0)
        }.onSuccess {
            analyticsRepository.log(BalanceIncreasedEvent(delta = 1000.0))
        }
    }

    fun onSubtract1000Dollars() {
        runCatching {
            bankRepository.subtractMoney(1000.0)
        }.onSuccess {
            analyticsRepository.log(BalanceDecreasedEvent(delta = 1000.0))
        }
    }
}
