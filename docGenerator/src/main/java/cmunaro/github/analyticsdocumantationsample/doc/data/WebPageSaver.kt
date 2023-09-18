package cmunaro.github.analyticsdocumantationsample.doc.data

import java.io.File

internal interface WebPageSaver {
    fun savePageToDocsFile(projectRoot: String, webPage: String): File
}

internal class WebPageSaverImpl: WebPageSaver {
    override fun savePageToDocsFile(projectRoot: String, webPage: String): File {
        val docsDirectory = File("$projectRoot/docs/")
        if (!docsDirectory.exists()) {
            docsDirectory.mkdirs()
        }
        val webPageFile = File("${docsDirectory.absoluteFile}/index.html")
        webPageFile.writeText(webPage)
        return webPageFile.absoluteFile
    }
}
