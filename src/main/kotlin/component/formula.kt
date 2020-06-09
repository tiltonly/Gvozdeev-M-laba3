package component

import react.*
import react.dom.*
import kotlinx.html.js.onClickFunction
import org.w3c.dom.events.Event
import data.*
import hoc.withDisplayName
import kotlinx.html.id
import org.w3c.dom.HTMLInputElement
import kotlin.browser.document
import kotlin.browser.window

interface FormulsProps : RProps {
    var formula: FormulaState
}

fun fFormula() =
    functionalComponent<FormulsProps> {
        p {
            it.formula.map{
                +it.value.name
            }
        }
    }

/*fun RBuilder.formula(
    formula: Formula
) = child(
    withDisplayName("Formuls", fFormula)
) {
    attrs.formula = formula

}
*/

/*interface FormulsEditProps : RProps {
    var formuls: Pair<Int, Student>
    var onClick: (Student) -> Unit
}

val fStudentEdit =
    functionalComponent<FormulsEditProps> { props ->
        span {
            input() {
                attrs.id = "Name${props.formuls.first}"
                attrs.defaultValue = props.formuls.second.firstname
            }
            button {
                +"Save"
                attrs.onClickFunction = {
                    val name = document
                        .getElementById("Name${props.formuls.first}")
                            as HTMLInputElement
                    props.onClick(Formula(name.value))
                    window.history.back()
                }
            }
        }
    }
*/