package cmunaro.github.analyticsdocumantationsample.doc.domain

import cmunaro.github.analyticsdocumantationsample.doc.data.InstancesFinder
import cmunaro.github.analyticsdocumantationsample.doc.data.InstancesFinderImpl
import cmunaro.github.analyticsdocumantationsample.doc.data.ProjectFilesDataSource
import cmunaro.github.analyticsdocumantationsample.doc.data.ProjectFilesDataSourceImpl
import cmunaro.github.analyticsdocumantationsample.doc.data.description
import cmunaro.github.analyticsdocumantationsample.doc.data.eventName
import cmunaro.github.analyticsdocumantationsample.doc.data.parameters
import cmunaro.github.analyticsdocumantationsample.doc.domain.model.EventImplementation
import cmunaro.github.analyticsdocumantationsample.doc.domain.model.EventRepresentation
import cmunaro.github.analyticsdocumantationsample.events.AnalyticsEvent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import java.io.File
import kotlin.reflect.KClass

interface EventSpider {
    suspend fun findEvents(): List<EventRepresentation>
}

internal class EventSpiderImpl(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val projectFilesDataSource: ProjectFilesDataSource = ProjectFilesDataSourceImpl(),
    private val instanceFinder: InstancesFinder = InstancesFinderImpl(dispatcher)
): EventSpider {
    private val scope = CoroutineScope(dispatcher)
    private val kotlinFiles: List<File> by lazy {
        projectFilesDataSource.getKotlinFiles()
    }
    private val swiftFiles: List<File> by lazy {
        projectFilesDataSource.getSwiftFiles()
    }

    override suspend fun findEvents(): List<EventRepresentation> = AnalyticsEvent::class
        .sealedSubclasses
        .map { kClass ->
            scope.async { eventToRepresentation(kClass) }
        }.awaitAll()

    private suspend fun eventToRepresentation(eventKClass: KClass<out AnalyticsEvent>): EventRepresentation {
        val eventClassName = eventKClass.simpleName!!
        val eventImplementation = EventImplementation(
            implementedByAndroid = instanceFinder.findInstanceOf(
                kClass = eventKClass,
                files = kotlinFiles
            ),
            implementedByIOS = instanceFinder.findInstanceOf(
                kClass = eventKClass,
                files = swiftFiles
            )
        )
        return EventRepresentation(
            className = eventClassName,
            eventName = eventKClass.eventName,
            description = eventKClass.description,
            parameters = eventKClass.parameters,
            eventImplementation = eventImplementation
        )
    }
}
