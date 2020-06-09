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

interface RulesProps : RProps {
    var rule: RulesState
}

fun fRule() =
    functionalComponent<RulesProps> {
        ol {
            it.rule.map {
                li {
                    +it.value.type
                }
            }
        }
    }
/*fun RBuilder.rule(
    rule: Rules
) = child(
    withDisplayName("Rules", fRule)
) {
    attrs.rule = rule

}
*/

/*interface RulesEditProps : RProps {
    var rules: Pair<Int, Rules>
    var onClick: (Rules) -> Unit
}

val fLessonEdit =
    functionalComponent<RulesEditProps> { props ->
        span {
            input() {
                attrs.id = "ruleEdit${props.rules.first}"
                attrs.defaultValue = props.rules.second.type
            }
            button {
                +"Save"
                attrs.onClickFunction = {
                    val inputElement = document
                        .getElementById("ruleEdit${props.rules.first}")
                            as HTMLInputElement
                    props.onClick(Rules(inputElement.value))
                    window.history.back()
                }
            }
        }
    }
*/