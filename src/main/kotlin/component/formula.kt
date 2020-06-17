package component

import react.*
import react.dom.*
import kotlinx.html.js.onClickFunction
import org.w3c.dom.events.Event
import data.*
import hoc.withDisplayName
import kotlinx.html.InputType
import kotlinx.html.id
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLInputElement
import react.router.dom.navLink
import kotlin.browser.document
import kotlin.browser.window

interface FormulsProps : RProps {
    var formula: FormulaState
    var add: (Event) -> Unit
}

fun fFormula() =
    functionalComponent<FormulsProps> { props ->
        button{
            +"AddFormula"
            attrs.onClickFunction = {
                val addwindow = document.getElementById("AddWindow") as HTMLElement
                addwindow.style.display = "block"
            }
        }
        table {
            tr {
                th{
                    +"Formula"
                }
                th{
                    +"ID"
                }
            }
            props.formula.map {
                tr{
                    td {
                        navLink("/formuls/${it.key}") {+it.value.name}
                    }
                    td {
                        +it.key.toString()
                    }
                }
            }
        }
        div {
            attrs.id = "AddWindow"
            div{
                h1 { +"AddFormula" }
                ul {
                    li {
                        +"Name"
                        input(InputType.text) {
                            attrs.id = "Name"
                        }
                    }
                    li {
                        +"IdOfRule"
                        input(InputType.text) {
                            attrs.id = "IDOfRule"
                        }
                    }
                    button{
                        +"Add"
                        attrs.onClickFunction = {props.add}
                    }
                    button{
                        +"Close"
                        attrs.onClickFunction = {
                            val editWindow = document
                                    .getElementById("AddWindow") as HTMLElement
                            editWindow.style.display = "none"
                        }
                    }
                }
            }
        }
    }

