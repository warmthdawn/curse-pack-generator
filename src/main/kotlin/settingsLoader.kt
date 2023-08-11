import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.nio.file.Files
import java.nio.file.Paths

data class Config(
    val apiKey: String,
    val gameVersion: String,
    val modLoaderType: ModLoaderType,
    val allowBeta: Boolean,
    val allowAlpha: Boolean,
)

suspend fun loadConfig(): Config {
    return withContext(Dispatchers.IO) {
        if (!Files.exists(Paths.get("config.json"))) {
            println("未找到config.json文件，请输入配置")
            println("请输入CurseForge的API KEY")
            val apiKey = readLine()!!
            println("请输入游戏版本")
            val gameVersion = readLine()!!
            println("请选择mod加载器类型：")
            println("1. Forge")
            println("2. Cauldron")
            println("3. LiteLoader")
            println("4. Fabric")
            println("5. Quilt")
            val modLoaderType = readLine()?.toIntOrNull()?.let {
                when (it) {
                    1 -> ModLoaderType.Forge
                    2 -> ModLoaderType.Cauldron
                    3 -> ModLoaderType.LiteLoader
                    4 -> ModLoaderType.Fabric
                    5 -> ModLoaderType.Quilt
                    else -> null
                }
            } ?: throw IllegalArgumentException("无效的mod加载器类型")

            val allowBeta = questionYesNo("是否允许beta版本(y/n)")
            val allowAlpha = questionYesNo("是否允许alpha版本(y/n)")
            val config = Config(apiKey, gameVersion, modLoaderType, allowBeta, allowAlpha)

            // write to file by org.json

            val json = JSONObject()
                .put("apiKey", config.apiKey)
                .put("gameVersion", config.gameVersion)
                .put("modLoaderType", config.modLoaderType.value)
                .put("allowBeta", config.allowBeta)
                .put("allowAlpha", config.allowAlpha)
            Files.write(Paths.get("config.json"), json.toString().toByteArray())
            config
        } else {
            val json = JSONObject(String(Files.readAllBytes(Paths.get("config.json"))))
            Config(
                apiKey = json.getString("apiKey"),
                gameVersion = json.getString("gameVersion"),
                modLoaderType = when (json.getInt("modLoaderType")) {
                    1 -> ModLoaderType.Forge
                    2 -> ModLoaderType.Cauldron
                    3 -> ModLoaderType.LiteLoader
                    4 -> ModLoaderType.Fabric
                    5 -> ModLoaderType.Quilt
                    else -> throw IllegalArgumentException("无效的mod加载器类型")
                },
                allowBeta = json.getBoolean("allowBeta"),
                allowAlpha = json.getBoolean("allowAlpha"),
            )
        }

    }
}

