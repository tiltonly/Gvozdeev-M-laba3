package redux

import data.State
import data.*
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.HTMLSelectElement
import kotlin.browser.document

fun editReducer(state: State, action: RAction) =
        when(action) {
            /*is EditFormula ->{
                val editWindow = document.getElementById("editWindow") as HTMLElement
                val editedName = document.getElementById("editedName") as HTMLInputElement
                val editedidOfRule = document.getElementById("editedIdOfRule") as HTMLInputElement
                val editFormula = Formula(
                        if(editedName.value == "") "no name" else editedName.value,
                        if(editedidOfRule.value == "") 0 else editedidOfRule.value.to()
                )
                editWindow.style.display ="none"
                State(
                        state.games.toMutableMap()
                                .apply { this[action.id] = editGame },
                        state.genres
                )
            }*/
            is EditRules -> {
                val editWindow = document.getElementById("editWindow") as HTMLElement
                val editedtype = document.getElementById("editedType") as HTMLSelectElement
                val editedidofresult = document.getElementById("editedIdOfResult") as HTMLInputElement
                val editednumber = document.getElementById("editedNumber") as HTMLInputElement
                val editRules = Rules(
                        editedtype.value,
                        editedidofresult.value.toInt(),
                        editednumber.value.toInt()
                )
                editWindow.style.display = "none"
                State(
                        state.Rules.toMutableMap()
                                .apply { this[action.id] = editRules },
                        state.Formula
                )
            }
            else -> state
        }

fun deleteReducer(state: State, action: RAction) =
        when (action) {
            is DeleteRules -> State(
                    state.Rules.minus(action.id),
                    state.Formula
            )
            is DeleteFormula -> State(
                    state.Rules,
                    state.Formula.minus(action.id))
            else -> state
        }


fun addReducer(state: State, action: RAction, newId: Int = -1) =
        when (action) {
            /*is AddFormula -> {
                val addWindow = document.getElementById("AddWindow") as HTMLElement
                val name = document.getElementById("name") as HTMLInputElement
                val idOfRule = document.getElementById("idOfRule") as HTMLInputElement
                val newFormula = Formula(
                        if(name.value == "") "no name" else name.value,
                        idOfRule.value.
                ) addWindow.style.dis
               play = "none"
                name.value = ""
                idOfRule.value = ""

                State(
                        state.games.plus(newId to newGame),
                        state.genres
                )

           }*/
            is AddRule ->{
                val addWindow = document.getElementById("AddWindow") as HTMLElement
                val idOfResult = document.getElementById("idOfResult") as HTMLInputElement
                val number = document.getElementById("number") as HTMLInputElement
                val type = document.getElementById("type") as HTMLSelectElement
                val newrule = Rules(
                       type.value,
                        idOfResult.value.toInt(),
                        number.value.toInt()
                )
                addWindow.style.display = "none"
                idOfResult.value =""
                number.value=""
                type.value=""
                State(
                        state.Rules.plus(newId to newrule),
                        state.Formula
                )
            }
            else -> state
        }

fun rootReducer(state: State, action: RAction) =
        when (action) {
            is AddFormula -> {
                val id = state.Formula.newId()
                addReducer(state, action, id)}
            is AddRule -> {
                val id = state.Rules.newId()
                addReducer(state,action,id)
            }
            is DeleteFormula -> deleteReducer(state, action)
            is DeleteRules -> deleteReducer(state, action)
            is EditFormula -> editReducer(state, action)
            is EditRules -> editReducer(state, action)
            else -> state
        }

