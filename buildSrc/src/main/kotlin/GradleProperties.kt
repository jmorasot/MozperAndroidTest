import com.google.common.base.Charsets
import org.jetbrains.kotlin.konan.properties.Properties
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader

object GradleProperties {
    private val properties = mutableMapOf<ProjectProperties, Properties>()

    enum class ProjectProperties(val file: String) {
        MOZPER_STAGE("app/config/properties/stage.properties"),
        MOZPER_PRODUCTION("app/config/properties/prod.properties"),
    }

    fun init(rootDir: File) {
        for (enum in ProjectProperties.values()) {
            val file = File(rootDir, enum.file)
            if (file.exists() && file.isFile) {
                loadProperties(enum, file)
            }
        }
    }

    private fun loadProperties(propertiesName: ProjectProperties, file: File) {
        val properties = Properties()
        InputStreamReader(FileInputStream(file), Charsets.UTF_8).use { reader ->
            properties.load(reader)
        }
        this.properties[propertiesName] = properties
    }

    fun has(propertiesName: ProjectProperties): Boolean {
        return properties.containsKey(propertiesName)
    }

    fun getProperty(propertiesName: ProjectProperties, key: String): String? {
        return properties[propertiesName]?.getProperty(key)
    }
}
