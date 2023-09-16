package cmunaro.github.analyticsdocumantationsample.doc

import cmunaro.github.analyticsdocumantationsample.doc.domain.DocGenerator
import cmunaro.github.analyticsdocumantationsample.doc.domain.DocGeneratorImpl
import cmunaro.github.analyticsdocumantationsample.doc.domain.EventSpider
import cmunaro.github.analyticsdocumantationsample.doc.domain.EventSpiderImpl

suspend fun main(args: Array<String>) {
    val eventSpider: EventSpider = EventSpiderImpl()
    val docGenerator: DocGenerator = DocGeneratorImpl()

    println("🔎 Finding events..")
    val events = eventSpider.findEvents()
    println("✅ Found ${events.size} events")

    println("✏️ Generating Doc..")
    val webPage = docGenerator.generateDocToFile(events)
    println(
        """
        |🚀 Doc generated!
        |📄 WebPage: ${webPage.absoluteFile}
        """.trimMargin()
    )
}
