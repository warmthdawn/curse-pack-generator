import java.io.File

val regex = Regex("https://www.curseforge.com/minecraft/mc-mods/([a-z0-9\\-_]+)")

/**
 * Loads the file and returns a list of mod ids
 */
fun loadFile(fileName: String): Array<String> {
    val file = File(fileName)
    return file.readLines().mapNotNull {
        val matchResult = regex.find(it)
        matchResult?.groupValues?.get(1)
    }.toTypedArray()
}