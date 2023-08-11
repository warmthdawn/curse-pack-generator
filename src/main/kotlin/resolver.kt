import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.await
import com.github.kittinunf.fuel.json.jsonDeserializer
import com.github.kittinunf.fuel.json.responseJson
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import org.json.JSONArray
import org.json.JSONObject

class Resolver(
    private val authKey: String,
    private val gameVersion: String = "1.18.2",
    private val modLoaderType: ModLoaderType = ModLoaderType.Forge,
    private val allowBeta: Boolean = false,
    private val allowAlpha: Boolean = false,
) {
    suspend fun findMod(modId: String): ModFileInfo {
        // send request using fuel
        // example: https://api.curseforge.com/v1/mods/search?gameId=432&slug=jei
        val json = Fuel.get("https://api.curseforge.com/v1/mods/search?gameId=432&classId=6&slug=$modId")
            .header("X-API-Key" to authKey, "Accept" to "application/json")
            .await(jsonDeserializer())

        val results = json.obj().getJSONArray("data")

        if (results.length() == 0) {
            println("未找到mod $modId, 请检查mod id是否正确")
            throw ModNotFoundException("未找到mod $modId, 请检查mod id是否正确", modId, json.obj().toString())
        }

        val mod = results.getJSONObject(0)

        return modFileInfo(mod)


    }

    private suspend fun modFileInfo(mod: JSONObject): ModFileInfo {
        val projectId = mod.getInt("id")
        val modId = mod.getString("slug")

        val modUrl = mod.getJSONObject("links")
            .getString("websiteUrl")

        val latestFileIndexes = mod.getJSONArray("latestFilesIndexes")
            .asSequence()
            .map { it as JSONObject }
            .map { parseFileIndex(it) }
            .filter { it.gameVersion == gameVersion }
            .filter { it.modLoader == modLoaderType || it.modLoader == null }
            .filter { (it.releaseType != FileReleaseType.Alpha || allowAlpha) && (it.releaseType != FileReleaseType.Beta || allowBeta) }
            .map { it.fileId }
            .toSet()


        if (latestFileIndexes.isNotEmpty()) {
            val latestFiles = mod.getJSONArray("latestFiles")
                .asSequence()
                .map { it as JSONObject }
                .map { parseFile(it) }
                .filter {
                    latestFileIndexes.contains(it.id)
                }
                .filter { it.gameVersions.contains(modLoaderType.name) }
                .sortedByDescending { it.fileDate }
                .toList()

            if (latestFiles.size == latestFileIndexes.size) {
                println("找到mod ${latestFiles[0].displayName}, fileId = ${latestFiles[0].id}")
                return ModFileInfo(projectId, latestFiles[0].id, getDependencyIds(latestFiles[0]), modUrl)
            }
        }

        // re request for file list
        // /v1/mods/{modId}/files/{fileId}
        val fileResp =
            Fuel.get("https://api.curseforge.com/v1/mods/$projectId/files?gameId=432&gameVersion=$gameVersion&modLoader=${modLoaderType.value}")
                .header("X-API-Key" to authKey, "Accept" to "application/json")
                .await(jsonDeserializer())

        val files = fileResp.obj().getJSONArray("data")
            .asSequence()
            .map { it as JSONObject }
            .map { parseFile(it) }
            .toList()

        var foundFile = files
            .asSequence()
            .filter { (it.releaseType != FileReleaseType.Alpha || allowAlpha) && (it.releaseType != FileReleaseType.Beta || allowBeta) }
            .filter { it.gameVersions.contains(modLoaderType.name) }
            .sortedByDescending { it.fileDate }
            .firstOrNull()

        if (foundFile != null) {
            println("找到mod ${foundFile.displayName}, fileId = ${foundFile.id}")
            return ModFileInfo(projectId, foundFile.id, getDependencyIds(foundFile), modUrl)
        } else {
            foundFile = files
                .asSequence()
                .filter { (it.releaseType != FileReleaseType.Alpha || allowAlpha) && (it.releaseType != FileReleaseType.Beta || allowBeta) }
                .filter { it.gameVersions.size == 1 && it.gameVersions[0] == gameVersion }
                .sortedByDescending { it.fileDate }
                .firstOrNull()

            if (foundFile != null) {
                println("未能找到 ${modLoaderType.name} 版本的mod， 但是找到mod ${foundFile.fileName}, fileId = ${foundFile.id}")
                return ModFileInfo(projectId, foundFile.id, getDependencyIds(foundFile), modUrl)
            }

            val msg: String? = if (files.isEmpty()) {
                "可能是链接不正确或者mod不支持$gameVersion"
            } else if (!allowAlpha && files.any { it.releaseType == FileReleaseType.Alpha }) {
                "该 mod 只有 alpha 版本"
            } else if (!allowBeta && files.any { it.releaseType == FileReleaseType.Beta }) {
                "该 mod 只有 beta 版本"
            } else if (!files.any { it.gameVersions.contains(modLoaderType.name) }) {
                val supportedVersions =
                    files.flatMap { it.gameVersions.asSequence() }.filter { loaders.contains(it) }.distinct()
                "该 mod 不支持 ${modLoaderType.name}，仅支持 ${supportedVersions.joinToString(", ")}"
            } else {
                "未知错误"
            }

            println("未找到mod $modId，$msg")
            throw ModNotFoundException("未找到mod $modId，$msg", modId, fileResp.obj().toString())
        }
    }

    private val availableModIds: MutableSet<Int> = mutableSetOf()
    private val notFoundMods: MutableList<String> = mutableListOf()
    suspend fun fetchModInfos(modList: List<ModFileInfo>): Pair<List<ModFileInfo>, Array<String>> {
        availableModIds.clear()
        notFoundMods.clear()
        availableModIds.addAll(modList.map { it.projectId })
        return Pair(fetchModInfosImpl(modList), notFoundMods.toTypedArray())
    }

    private suspend fun fetchModInfosImpl(modList: List<ModFileInfo>): List<ModFileInfo> {
        // POST /v1/mods HTTP/1.1
        //
        //Content-Type: application/json
        //Accept: application/json
        val missingDependencies = modList.asSequence().flatMap { it.dependencyIds.asSequence() }
            .filter { !availableModIds.contains(it) }
            .toSet()

        if (missingDependencies.isEmpty()) {
            return emptyList()
        }

        val json = Fuel.post("https://api.curseforge.com/v1/mods")
            .header("X-API-Key" to authKey, "Accept" to "application/json", "Content-Type" to "application/json")
            .body(JSONObject().put("modIds", JSONArray().putAll(missingDependencies)).toString())
            .await(jsonDeserializer())

        val mods = json.obj().getJSONArray("data")

        val depsFileInfos = coroutineScope {
            mods.map {
                async {
                    kotlin.runCatching {
                        modFileInfo(it as JSONObject)
                    }
                }
            }.awaitAll()
        }

        availableModIds.addAll(depsFileInfos.mapNotNull { it.getOrNull()?.projectId })
        val result = mutableListOf<ModFileInfo>()
        depsFileInfos.forEach {
            if (it.isSuccess) {
                result.add(it.getOrThrow())
            } else {
                val exception = it.exceptionOrNull() as ModNotFoundException
                notFoundMods.add(exception.message ?: "未找到mod ${exception.modId}， 未知错误")
                println(exception.json)
            }
        }

        result.addAll(fetchModInfosImpl(result))
        return result

    }

    suspend fun getLoaderVersion(): String? {
//        https://api.curseforge.com/v1/minecraft/modloader?version=1.18.2&includeAll=true
        return Fuel.get("https://api.curseforge.com/v1/minecraft/modloader?version=$gameVersion&includeAll=true")
            .header("X-API-Key" to authKey, "Accept" to "application/json", "Content-Type" to "application/json")
            .await(jsonDeserializer())
            .obj()
            .getJSONArray("data")
            .asSequence()
            .map { it as JSONObject }
            .filter { it.getInt("type") == modLoaderType.value }
            .filter { it.getBoolean("latest") }
            .map { it.getString("name") }
            .firstOrNull()
    }
}

data class ModFileInfo(
    val projectId: Int,
    val fileId: Int,
    val dependencyIds: IntArray,
    val url: String,
) {
}

fun getDependencyIds(file: File): IntArray {
    return file.dependencies
        .filter {
            it.relationType == FileRelationType.RequiredDependency
        }
        .map { it.modId }
        .toIntArray()
}

class ModNotFoundException(
    message: String,
    val modId: String,
    val json: String,
) : Exception(message)