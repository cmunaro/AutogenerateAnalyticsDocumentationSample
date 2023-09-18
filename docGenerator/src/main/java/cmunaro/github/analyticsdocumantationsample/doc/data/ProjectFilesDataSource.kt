package cmunaro.github.analyticsdocumantationsample.doc.data

import java.io.File

interface ProjectFilesDataSource {
    fun getKotlinFiles(root: String): List<File>
    fun getSwiftFiles(root: String): List<File>
}

class ProjectFilesDataSourceImpl : ProjectFilesDataSource {

    override fun getKotlinFiles(root: String): List<File> =
        getFiles(root = root, upperDirectory = "androidApp", extension = "kt")

    override fun getSwiftFiles(root: String): List<File> =
        getFiles(root = root, upperDirectory = "iosApp", extension = "swift")

    private fun getFiles(root: String, upperDirectory: String, extension: String): List<File> =
        File(root).walk()
            .first { it.isDirectory && it.name == upperDirectory }
            .walk()
            .filter { it.name.endsWith(".$extension") }
            .toList()
            .map { file ->
                file.absoluteFile
            }
}
