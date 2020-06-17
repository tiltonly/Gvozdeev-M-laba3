package data

typealias RulesState = Map<Int, Rules>

typealias FormulaState = Map<Int, Formula>

class State(
        val Rules: RulesState,
        val Formula: FormulaState
)

fun <T> Map<Int, T>.newId() =
        (this.maxBy { it.key }?.key ?: 0) + 1

fun initialState() =
    State(
        listOfRules.mapIndexed { index, rules ->
            index to rules
        }.toMap(),
        listOfFormula.mapIndexed { index, formula ->
            index to formula
        }.toMap()
    )
