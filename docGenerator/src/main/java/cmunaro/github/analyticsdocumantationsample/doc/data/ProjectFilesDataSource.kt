package cmunaro.github.analyticsdocumantationsample.doc.data

import java.io.File

interface ProjectFilesDataSource {
    fun getKotlinFiles(): List<File>
    fun getSwiftFiles(): List<File>
}

class ProjectFilesDataSourceImpl : ProjectFilesDataSource {

    override fun getKotlinFiles(): List<File> =
        getFiles(upperDirectory = "androidApp", extension = "kt")

    override fun getSwiftFiles(): List<File> =
        getFiles(upperDirectory = "iosApp", extension = "swift")

    private fun getFiles(upperDirectory: String, extension: String): List<File> =
        File(".").walk()
            .first { it.isDirectory && it.name == upperDirectory }
            .walk()
            .filter { it.name.endsWith(".$extension") }
            .toList()
            .map { file ->
                file.absoluteFile
            }
}
