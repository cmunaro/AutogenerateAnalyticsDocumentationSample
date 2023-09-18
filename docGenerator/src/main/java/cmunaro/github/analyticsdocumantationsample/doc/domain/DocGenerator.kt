package cmunaro.github.analyticsdocumantationsample.doc.domain

import cmunaro.github.analyticsdocumantationsample.doc.data.WebPageProvider
import cmunaro.github.analyticsdocumantationsample.doc.data.WebPageProviderImpl
import cmunaro.github.analyticsdocumantationsample.doc.data.WebPageSaver
import cmunaro.github.analyticsdocumantationsample.doc.data.WebPageSaverImpl
import cmunaro.github.analyticsdocumantationsample.doc.domain.model.EventRepresentation

internal interface DocGenerator {
    fun generateDocToFile(projectRoot: String, events: List<EventRepresentation>)
}

internal class DocGeneratorImpl(
    private val webPageSaver: WebPageSaver = WebPageSaverImpl(),
    private val webPageCreator: WebPageProvider = WebPageProviderImpl()
) : DocGenerator {
    override fun generateDocToFile(projectRoot: String, events: List<EventRepresentation>) {
        val webPage = webPageCreator.createEventDocumentationPage(events = events)
        webPageSaver.savePageToDocsFile(projectRoot, webPage)
    }
}
