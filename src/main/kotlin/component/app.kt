package component

import container.*
import data.*
import react.*
import react.dom.*
import react.router.dom.*

interface AppProps : RProps {

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
        }
    }

