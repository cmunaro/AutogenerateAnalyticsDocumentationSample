package cmunaro.github.analyticsdocumantationsample.bank.data

import cmunaro.github.analyticsdocumantationsample.bank.domain.BankRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

internal class WonderfulBankRepository(initialAmount: Double): BankRepository {
    override val balance = MutableStateFlow(initialAmount)

    override fun addMoney(amount: Double) {
        check(amount > 0) { "You must provide a positive number!" }
        balance.update { currentAmount ->
            currentAmount + amount
        }
    }

    override fun subtractMoney(amount: Double) {
        check(amount > 0) { "You must provide a positive number!" }
        balance.update { currentAmount ->
            currentAmount - amount
        }
    }
}
