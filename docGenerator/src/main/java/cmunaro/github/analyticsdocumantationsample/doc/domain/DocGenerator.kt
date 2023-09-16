package cmunaro.github.analyticsdocumantationsample.doc.domain

import cmunaro.github.analyticsdocumantationsample.doc.data.WebPageProvider
import cmunaro.github.analyticsdocumantationsample.doc.data.WebPageProviderImpl
import cmunaro.github.analyticsdocumantationsample.doc.data.WebPageSaver
import cmunaro.github.analyticsdocumantationsample.doc.data.WebPageSaverImpl
import cmunaro.github.analyticsdocumantationsample.doc.domain.model.EventRepresentation
import java.io.File

internal interface DocGenerator {
    fun generateDocToFile(events: List<EventRepresentation>): File
}

internal class DocGeneratorImpl(
    private val webPageSaver: WebPageSaver = WebPageSaverImpl(),
    private val webPageCreator: WebPageProvider = WebPageProviderImpl()
) : DocGenerator {
    override fun generateDocToFile(events: List<EventRepresentation>): File {
        val webPage = webPageCreator.createEventDocumentationPage(events = events)
        return webPageSaver.savePageToFile(webPage)
    }
}
