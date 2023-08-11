import kotlinx.coroutines.*
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import java.util.concurrent.ConcurrentHashMap

// $2a$10$SIN47JoWb3aPO8/ai8EJF.NpljRITq3lAegG1DdkhCGA69YZwDMYW
suspend fun main(args: Array<String>) {
    start()
}


suspend fun start(): Boolean {
    val config = loadConfig()
    val resolver = Resolver(
        authKey = config.apiKey,
        gameVersion = config.gameVersion,
        modLoaderType = config.modLoaderType,
        allowBeta = config.allowBeta,
        allowAlpha = config.allowAlpha,
    )

    println("请输入mod列表文件")
    val fileName = readLine()?.trim(' ', '"') ?: return false
    val modIds = loadFile(fileName)

    println("找到${modIds.size}个mod，尝试解析中...")

    val notFoundMods = ConcurrentHashMap<String, String>()
    val modFileInfos = coroutineScope {
        modIds.map { modId ->
            async {
                try {
                    resolver.findMod(modId)
                } catch (e: ModNotFoundException) {
                    notFoundMods[e.modId] = e.message ?: "未找到mod ${e.modId}， 未知错误"
                    println(e.json)
                    null
                } catch (e: Exception) {
                    println("解析mod $modId 时出错")
                    e.printStackTrace()
                    null
                }
            }
        }.awaitAll().filterNotNull()
    }
    println("解析完成，共找到${modFileInfos.size}个mod, 未找到${notFoundMods.size}个mod")

    val availableModIds = modFileInfos.asSequence().map { it.projectId }.toSet()


    val missingDependencies = modFileInfos.asSequence().flatMap { it.dependencyIds.asSequence() }
        .filter { !availableModIds.contains(it) }
        .toSet()

    val (missingDeps, errors) = if (missingDependencies.isNotEmpty()) {
        println("缺少至少${missingDependencies.size}个依赖，正在解析：")

        resolver.fetchModInfos(modFileInfos)

    } else {
        Pair(emptyList(), emptyArray())
    }

    println("缺少${missingDeps.size}个依赖：")
    missingDeps.forEach {
        println(it.url)
    }
    println("未找到的mod：")
    notFoundMods.forEach {
        println(it.value)
    }
    errors.forEach {
        println(it)
    }


    println("--------------------")
    if (!questionYesNo("解析完成，是否需要生成 manifest.json 文件？(y/n)")) {
        return true
    }

    val addMissingDeps = missingDeps.isNotEmpty() &&
            questionYesNo("缺少${missingDeps.size}个依赖，是否尝试在manifest.json中添加这些依赖？(y/n)")



    println("请输入整合包名称：")
    val packName = readLine() ?: "未命名整合包"


    var loaderVersion = resolver.getLoaderVersion()

    if (loaderVersion == null) {
        println("无法找到对应的mod加载器版本，请手动输入")
        loaderVersion = readLine()?.trim() ?: return false
    }

    val json = generateManifest(
        packName = packName,
        mcVersion = config.gameVersion,
        modLoaderVersion = loaderVersion,
        mods = if (addMissingDeps) modFileInfos + missingDeps else modFileInfos,
    )

    // write to file

    withContext(Dispatchers.IO) {
        Files.write(
            Paths.get("manifest.json"),
            json.toByteArray()
        )
    }

    println("manifest.json已生成, 回车退出")
    readLine()

    return true
}

