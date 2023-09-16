package cmunaro.github.analyticsdocumantationsample.analytics.data

import cmunaro.github.analyticsdocumantationsample.analytics.domain.AnalyticsRepository
import cmunaro.github.analyticsdocumantationsample.events.AnalyticsEvent

internal class AnalyticsRepositoryImpl: AnalyticsRepository {
    override fun log(event: AnalyticsEvent) {
        println("Log event: $event")
    }
}
