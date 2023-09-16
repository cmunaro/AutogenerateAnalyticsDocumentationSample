package cmunaro.github.analyticsdocumantationsample.events

import cmunaro.github.analyticsdocumantationsample.Description

@Description("The user increased his money amount")
data class BalanceIncreasedEvent(
    @Description("Amount of money added to the account") val delta: Double
) : AnalyticsEvent() {
    override val name: String = "asd"
}

@Description("The user has decreased his money amount")
data class BalanceDecreasedEvent(
    @Description("Amount of money removed from the account") val delta: Double
) : AnalyticsEvent() {
    override val name: String = "qwe"
}
