import java.util.*

fun questionYesNo(question: String): Boolean {
    println(question)
    var answer: String? = readLine()?.trim()?.lowercase(Locale.getDefault())
    while (answer != "y" && answer != "n") {
        println("请输入 y 或 n")
        answer = readLine()?.trim()?.toLowerCase()
    }
    return answer == "y"
}