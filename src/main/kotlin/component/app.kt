package component

import container.*
import data.*
import react.*
import react.dom.*
import react.router.dom.*

interface AppProps : RProps {
    var Rules: RulesState
    var Formula: FormulaState
}
interface RouteNumberResult : RProps {
    var number: String
}

fun fApp() =
    functionalComponent<AppProps> { props ->
        header {
            h1 { +"Расчет рейтинга" }
            nav {
                ul {
                    li { navLink("/rules") { +"Rules" } }
                    li { navLink("/formuls") { +"Formuls" } }
                }
            }
        }

        switch {
            route("/rules",
                    exact = true,
                    render = { listOfRulesContainer{ } }
            )
            route("/formuls",
                    exact = true,
                    render = { listOfFormulaContainer{} }
            )
            route("/formuls/:number",
                    exact = true,
                    render = renderObject(
                            {
                                props.Formula[it]
                            },
                            {   index,_->
                                formulaPageContainer{attrs.index = index}
                            }
                    )
            )
            route("/rules/:number",
                    exact = true,
                    render = renderObject(
                            {
                                props.Rules[it]
                            },
                            {   index,_->
                                rulesPageContainer{attrs.index = index}
                            }
                    )
            )
        }
    }

fun <O> RBuilder.renderObject(
        selector: (Int) -> O?,
        rElement: (Int, O) -> ReactElement
) =
        { route_props: RouteResultProps<RouteNumberResult> ->
            val num = route_props.match.params.number.toIntOrNull() ?: -1
            val obj = selector(num)
            if (obj != null)
                rElement(num, obj)
            else
                p { +"Object not found" }
        }

