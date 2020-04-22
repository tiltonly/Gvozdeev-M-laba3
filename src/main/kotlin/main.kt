import kotlin.browser.document
import kotlin.js.Date

fun main() {
    val date = Date()
    val date1 = "1.01.2021 00:00"
    val date2 = Date(date1)
    val mlsPerDay = 24*60*60*1000
    val daysuntiltheNewYear = ((date2.getTime() - date.getTime())/mlsPerDay).toInt()
    document.write("Сегодняшняя дата: $date ")
    document.write("<br> Дней до нового года: $daysuntiltheNewYear")
}
