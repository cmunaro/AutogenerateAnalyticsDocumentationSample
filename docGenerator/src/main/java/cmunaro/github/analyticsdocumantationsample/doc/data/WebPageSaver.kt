package cmunaro.github.analyticsdocumantationsample.doc.data

import java.io.File

internal interface WebPageSaver {
    fun savePageToFile(webPage: String): File
}

internal class WebPageSaverImpl: WebPageSaver {
    override fun savePageToFile(webPage: String): File {
        val webPageFile = File("index.html")
        webPageFile.writeText(webPage)
        return webPageFile.absoluteFile
    }
}
