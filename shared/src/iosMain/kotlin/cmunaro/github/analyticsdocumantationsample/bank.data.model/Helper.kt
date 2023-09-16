@file:Suppress("UNUSED")
package cmunaro.github.analyticsdocumantationsample.bank.data.model

import cmunaro.github.analyticsdocumantationsample.analytics.domain.AnalyticsRepository
import cmunaro.github.analyticsdocumantationsample.bank.domain.BankRepository
import cmunaro.github.analyticsdocumantationsample.di.sharedModule
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

object DependenciesProviderHelper : KoinComponent {
    fun initKoin() {
        startKoin {
            modules(sharedModule)
        }
    }

    val bankRepository: BankRepository by inject()
    val analyticsRepository: AnalyticsRepository by inject()
}
