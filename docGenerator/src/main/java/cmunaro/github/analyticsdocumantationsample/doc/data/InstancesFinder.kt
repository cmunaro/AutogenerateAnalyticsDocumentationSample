package cmunaro.github.analyticsdocumantationsample.doc.data

import cmunaro.github.analyticsdocumantationsample.events.AnalyticsEvent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import java.io.File
import kotlin.reflect.KClass

internal interface InstancesFinder {
    suspend fun findInstanceOf(kClass: KClass<out AnalyticsEvent>, files: List<File>): Boolean
}

internal class InstancesFinderImpl(dispatcher: CoroutineDispatcher): InstancesFinder {
    private val scope = CoroutineScope(dispatcher)

    override suspend fun findInstanceOf(kClass: KClass<out AnalyticsEvent>, files: List<File>): Boolean {
        val instantiations = files.map {
            scope.async {
                val fileContent = it.readLines().joinToString("\n")
                fileContent.removeSingleLineComments()
                    .removeMultiLineComments()
                    .countClassInstantiationInFile(kClass)
            }
        }.awaitAll()
            .sum()
        return instantiations > 0
    }

    private fun String.removeSingleLineComments(): String {
        val regex = Regex("""//.*""")
        return regex.replace(this) { matchResult ->
            if (isWithinQuotes(matchResult.value)) {
                matchResult.value
            } else {
                matchResult.value.replace(regex, "")
            }
        }
    }

    private fun String.removeMultiLineComments(): String {
        val regex = Regex("""/\*.*\*/""")
        return regex.replace(this) { matchResult ->
            if (isWithinQuotes(matchResult.value)) {
                matchResult.value
            } else {
                matchResult.value.replace(regex, "")
            }
        }
    }

    private fun isWithinQuotes(s: String): Boolean =
        s.startsWith("\"") && s.endsWith("\"")

    private fun String.countClassInstantiationInFile(kClass: KClass<out AnalyticsEvent>): Int {
        val className = kClass.simpleName!!
        val regex = Regex("""$className\([^)]*\)""")
        return regex.findAll(this).count()
    }
}
