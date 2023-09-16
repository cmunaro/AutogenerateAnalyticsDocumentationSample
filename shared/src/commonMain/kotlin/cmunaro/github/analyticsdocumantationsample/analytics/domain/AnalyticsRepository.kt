package cmunaro.github.analyticsdocumantationsample.analytics.domain

import cmunaro.github.analyticsdocumantationsample.events.AnalyticsEvent

interface AnalyticsRepository {
    fun log(event: AnalyticsEvent)
}
