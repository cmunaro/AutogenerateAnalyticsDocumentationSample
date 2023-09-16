package cmunaro.github.analyticsdocumantationsample.doc.domain.model

data class EventRepresentation(
    val className: String,
    val eventName: String,
    val description: String,
    val parameters: List<ParameterRepresentation>,
    val eventImplementation: EventImplementation
)

data class ParameterRepresentation(
    val parameterName: String,
    val description: String,
    val typeName: String
)

data class EventImplementation(
    val implementedByAndroid: Boolean,
    val implementedByIOS: Boolean
)
