package cmunaro.github.analyticsdocumantationsample.events

import cmunaro.github.analyticsdocumantationsample.Description

@Description("The user increased his money amount")
data class BalanceIncreasedEvent(
    @Description("Amount of money added to the account") val delta: Double
) : AnalyticsEvent(name = "balance_increased")

@Description("The user has decreased his money amount")
data class BalanceDecreasedEvent(
    @Description("Amount of money removed from the account") val delta: Double
) : AnalyticsEvent(name = "balance_decreased")

@Description("Evento di demo")
data class Evviva(
    @Description("Blabla") val ciao: Double
) : AnalyticsEvent(name = "demo_event")


