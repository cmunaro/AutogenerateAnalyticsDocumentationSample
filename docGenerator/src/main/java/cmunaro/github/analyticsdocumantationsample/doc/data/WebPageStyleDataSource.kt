package cmunaro.github.analyticsdocumantationsample.doc.data

internal interface WebPageStyleDataSource {
    val style: String
}

class WebPageStyleDataSourceImpl : WebPageStyleDataSource {
    override val style: String
        get() = ClassLoader.getSystemResource("style.css")
            ?.readText()
            .orEmpty()
}
