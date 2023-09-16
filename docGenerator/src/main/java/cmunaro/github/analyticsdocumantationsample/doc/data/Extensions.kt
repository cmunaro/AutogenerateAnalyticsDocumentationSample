package cmunaro.github.analyticsdocumantationsample.doc.data

import cmunaro.github.analyticsdocumantationsample.Description
import cmunaro.github.analyticsdocumantationsample.doc.domain.model.ParameterRepresentation
import cmunaro.github.analyticsdocumantationsample.events.AnalyticsEvent
import kotlin.reflect.KClass
import kotlin.reflect.KParameter
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.jvm.javaType

val KClass<out AnalyticsEvent>.description: String
    get() {
        val descriptions = annotations.filterIsInstance<Description>()
        check(descriptions.size == 1) {
            "$simpleName has ${descriptions.size} descriptions, it must have 1"
        }
        return descriptions.first().value
    }

val KClass<out AnalyticsEvent>.eventName: String
    get() = generateDummyInstance().name

fun <T : Any> KClass<T>.generateDummyInstance(): T {
    val constructorParameters = primaryConstructor!!.parameters
        .associateWith { param -> getValueFor(param) }
    return primaryConstructor!!.callBy(constructorParameters)
}

fun getValueFor(kParameter: KParameter): Any? {
    if (kParameter.type.isMarkedNullable) {
        return null
    }
    return when (kParameter.type.classifier) {
        Boolean::class -> false
        Char::class -> ' '
        Short::class -> 0
        Int::class -> 0
        Float::class -> 0f
        Double::class -> 0.0
        String::class -> ""
        else -> {
            val clazz = kParameter.type.javaType as Class<*>
            when {
                clazz.isEnum -> {
                    val enumClass = clazz.enumConstants.first()::class.java
                    enumClass.enumConstants.first()
                }

                else -> runCatching { clazz.kotlin.generateDummyInstance() }
                    .onFailure { throw Exception("Unhandled default type for $kParameter") }
            }
        }
    }
}

val KClass<out AnalyticsEvent>.parameters: List<ParameterRepresentation>
    get() = primaryConstructor?.parameters?.map(::toParameterRepresentation).orEmpty()

fun toParameterRepresentation(parameter: KParameter): ParameterRepresentation {
    val descriptions = parameter.annotations
        .filterIsInstance<Description>()
    check(descriptions.size == 1) {
        "${parameter.name} has ${descriptions.size} descriptions, it must have 1"
    }
    return ParameterRepresentation(
        parameterName = parameter.name!!,
        description = descriptions.first().value,
        typeName = parameter.type.javaType.typeName
    )
}