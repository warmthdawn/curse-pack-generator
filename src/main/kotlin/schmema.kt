import org.json.JSONObject
import java.time.Instant


data class File(
    val id: Int,
    val gameId: Int,
    val modId: Int,
    val isAvailable: Boolean,
    val displayName: String,
    val fileName: String,
    val fileDate: Instant,
    val releaseType: FileReleaseType,
    val gameVersions: Array<String>,

    val dependencies: Array<FileDependency>,
    val isServerPack: Boolean,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as File

        if (id != other.id) return false
        if (gameId != other.gameId) return false
        if (modId != other.modId) return false
        if (isAvailable != other.isAvailable) return false
        if (displayName != other.displayName) return false
        if (fileName != other.fileName) return false
        if (fileDate != other.fileDate) return false
        if (releaseType != other.releaseType) return false
        if (!gameVersions.contentEquals(other.gameVersions)) return false
        if (!dependencies.contentEquals(other.dependencies)) return false
        if (isServerPack != other.isServerPack) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + gameId
        result = 31 * result + modId
        result = 31 * result + isAvailable.hashCode()
        result = 31 * result + displayName.hashCode()
        result = 31 * result + fileName.hashCode()
        result = 31 * result + fileDate.hashCode()
        result = 31 * result + releaseType.hashCode()
        result = 31 * result + gameVersions.contentHashCode()
        result = 31 * result + dependencies.contentHashCode()
        result = 31 * result + isServerPack.hashCode()
        return result
    }

}

data class FileDependency(
    val modId: Int,
    val relationType: FileRelationType
)

enum class FileRelationType(val value: Int) {
    EmbeddedLibrary(1),
    OptionalDependency(2),
    RequiredDependency(3),
    Tool(4),
    Incompatible(5),
    Include(6)
}

enum class FileReleaseType(val value: Int) {
    Alpha(1),
    Beta(2),
    Release(3)
}


fun parseFile(jsonObject: JSONObject): File {
    try {


        // parse json
        val id = jsonObject.getInt("id")
        val gameId = jsonObject.getInt("gameId")
        val modId = jsonObject.getInt("modId")
        val isAvailable = jsonObject.getBoolean("isAvailable")
        val displayName = jsonObject.getString("displayName")
        val fileName = jsonObject.getString("fileName")
        // 2022-11-23T13:44:10.997Z
        val fileDate = Instant.parse(jsonObject.getString("fileDate"))
        val releaseType = when (jsonObject.getInt("releaseType")) {
            1 -> FileReleaseType.Alpha
            2 -> FileReleaseType.Beta
            3 -> FileReleaseType.Release
            else -> throw IllegalArgumentException("Unknown release type")
        }
        val gameVersions = jsonObject.getJSONArray("gameVersions").map { it as String }.toTypedArray()
        val dependencies =
            jsonObject.getJSONArray("dependencies").map { parseFileDependency(it as JSONObject) }.toTypedArray()
        val isServerPack = jsonObject.getBoolean("isServerPack")

        return File(
            id,
            gameId,
            modId,
            isAvailable,
            displayName,
            fileName,
            fileDate,
            releaseType,
            gameVersions,
            dependencies,
            isServerPack
        )
    } catch (e: Exception) {
        println("Error parsing file: $jsonObject")
        throw e
    }
}

fun parseFileDependency(jsonObject: JSONObject): FileDependency {
    val modId = jsonObject.getInt("modId")
    val relationType = when (jsonObject.getInt("relationType")) {
        1 -> FileRelationType.EmbeddedLibrary
        2 -> FileRelationType.OptionalDependency
        3 -> FileRelationType.RequiredDependency
        4 -> FileRelationType.Tool
        5 -> FileRelationType.Incompatible
        6 -> FileRelationType.Include
        else -> throw IllegalArgumentException("Unknown relation type")
    }
    return FileDependency(modId, relationType)
}

data class FileIndex(
    val gameVersion: String,
    val fileId: Int,
    val fileName: String,
    val releaseType: FileReleaseType,
    val gameVersionTypeId: Int?,
    val modLoader: ModLoaderType?,
)

enum class ModLoaderType(val value: Int) {
    Any(0),
    Forge(1),
    Cauldron(2),
    LiteLoader(3),
    Fabric(4),
    Quilt(5),
}

val loaders = arrayOf("Forge", "Cauldron", "LiteLoader", "Fabric", "Quilt");

fun parseFileIndex(jsonObject: JSONObject): FileIndex {
    try {
        val gameVersion = jsonObject.getString("gameVersion")
        val fileId = jsonObject.getInt("fileId")
        val fileName = jsonObject.getString("filename")
        val releaseType = when (jsonObject.getInt("releaseType")) {
            1 -> FileReleaseType.Alpha
            2 -> FileReleaseType.Beta
            3 -> FileReleaseType.Release
            else -> throw IllegalArgumentException("Unknown release type")
        }
        val gameVersionTypeId = jsonObject.get("gameVersionTypeId") as? Int
        val modLoader = if (jsonObject.isNull("modLoader")) {
            null
        } else when (jsonObject.getInt("modLoader")) {
            0 -> ModLoaderType.Any
            1 -> ModLoaderType.Forge
            2 -> ModLoaderType.Cauldron
            3 -> ModLoaderType.LiteLoader
            4 -> ModLoaderType.Fabric
            5 -> ModLoaderType.Quilt
            else -> throw IllegalArgumentException("Unknown mod loader type")
        }
        return FileIndex(gameVersion, fileId, fileName, releaseType, gameVersionTypeId, modLoader)
    } catch (e: Exception) {
        println("Failed to parse file index: $jsonObject")
        throw e
    }
}
