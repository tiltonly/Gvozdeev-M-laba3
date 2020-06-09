package data

data class Formula(
    val id: Int,
    val name: String,
    val idOfRule: Int
)

val listOfFormula = arrayListOf(Formula(1,"Формула расчета рейтинга учащихся: ", 1))

