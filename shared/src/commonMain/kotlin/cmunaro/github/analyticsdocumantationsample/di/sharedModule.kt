package cmunaro.github.analyticsdocumantationsample.di

import cmunaro.github.analyticsdocumantationsample.bank.domain.BankRepository
import cmunaro.github.analyticsdocumantationsample.bank.data.WonderfulBankRepository
import cmunaro.github.analyticsdocumantationsample.analytics.data.AnalyticsRepositoryImpl
import cmunaro.github.analyticsdocumantationsample.analytics.domain.AnalyticsRepository
import cmunaro.github.analyticsdocumantationsample.bank.data.model.initialPlatformAmount
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val sharedModule = module {
    factoryOf(::AnalyticsRepositoryImpl) bind AnalyticsRepository::class

    single<BankRepository> {
        WonderfulBankRepository(initialAmount = initialPlatformAmount)
    }
}
