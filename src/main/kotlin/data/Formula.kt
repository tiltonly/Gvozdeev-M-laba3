package data

data class Formula(
    val name: String,
    val idOfRule: Array<Int>
)

val listOfFormula = arrayListOf(Formula("Формула расчета рейтинга учащихся:", arrayOf(0,1,2,3,4,5)))

