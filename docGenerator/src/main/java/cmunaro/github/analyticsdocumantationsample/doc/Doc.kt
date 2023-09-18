package cmunaro.github.analyticsdocumantationsample.doc

import cmunaro.github.analyticsdocumantationsample.doc.domain.DocGenerator
import cmunaro.github.analyticsdocumantationsample.doc.domain.DocGeneratorImpl
import cmunaro.github.analyticsdocumantationsample.doc.domain.EventSpider
import cmunaro.github.analyticsdocumantationsample.doc.domain.EventSpiderImpl

private const val DEFAULT_PROJECT_ROOT = "."

suspend fun main(args: Array<String>) {
    val projectRoot = args.firstOrNull() ?: DEFAULT_PROJECT_ROOT
    val eventSpider: EventSpider = EventSpiderImpl(projectRoot = projectRoot)
    val docGenerator: DocGenerator = DocGeneratorImpl()

    println("🔎 Finding events..")
    val events = eventSpider.findEvents()
    println("✅ Found ${events.size} events")

    println("✏️ Generating Doc..")
    docGenerator.generateDocToFile(projectRoot = projectRoot, events = events)
    println("🚀 Doc generated!")
}
