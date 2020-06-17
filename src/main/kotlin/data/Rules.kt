package data

data class Rules(
    val type: String,
    val idOfResult: Int,
    val number: Int
)

val listOfRules = arrayListOf( Rules("Условие входа (0) ", 0,0),
                            Rules("Условие входа (1) ", 0,0),
                            Rules("Условие входа (2) ", 0,0),
                            Rules("Условие входа (3) ", 0,0),
                            Rules("Условие входа (4) ", 0,0),
                            Rules("Условие входа (5) ", 0,0),
                            Rules( "Условие результата (0) ", 0, 0),
                            Rules( "Условие результата (1) ", 0, 0),
                            Rules( "Условие результата (2) ", 0, 0),
                            Rules( "Условие результата (3) ", 0, 0),
                            Rules( "Условие результата (4) ", 0, 0),
                            Rules( "Условие результата (5) ", 0, 0),
                            Rules ( "Коэффициент", 0, 0))