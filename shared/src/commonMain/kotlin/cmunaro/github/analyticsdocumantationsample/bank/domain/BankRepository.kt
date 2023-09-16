package cmunaro.github.analyticsdocumantationsample.bank.domain

import kotlinx.coroutines.flow.StateFlow

interface BankRepository {
    val balance: StateFlow<Double>

    @Throws(IllegalArgumentException::class)
    fun subtractMoney(amount: Double)

    @Throws(IllegalArgumentException::class)
    fun addMoney(amount: Double)
}
