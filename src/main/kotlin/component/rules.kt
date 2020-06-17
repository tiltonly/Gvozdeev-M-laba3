package component

import react.*
import react.dom.*
import kotlinx.html.js.onClickFunction
import org.w3c.dom.events.Event
import data.*
import hoc.withDisplayName
import kotlinx.html.InputType
import kotlinx.html.id
import kotlinx.html.style
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLInputElement
import react.router.dom.navLink
import redux.AddRule
import kotlin.browser.document
import kotlin.browser.window

interface RulesProps : RProps {
    var rule: Array<Rules>
    var add: (Event) -> Unit
}

val fRule =
    functionalComponent<RulesProps> { props ->
        button {
            +"AddRule"
            attrs.onClickFunction = {
                val addwindow = document.getElementById("AddWindow") as HTMLElement
                addwindow.style.display = "block"
            }
        }
        table {
            tr {
                th {
                    +"type"
                }
                th {
                    +"ID"
                }
            }
            props.rule.mapIndexed{ index, rule ->
                tr {
                    td {
                        navLink("/rules/${index}") { +rule.type }
                    }
                    td {
                        +index.toString()
                    }
                }
            }
        }
        div("window") {
            attrs.id = "AddWindow"
            div {
                h1 { +"AddRules" }
                ul {
                    li {
                        +"Type"
                        select {
                            for (element in typeslist) {
                                attrs.id = "type"
                                option {
                                    +element.type
                                }
                            }
                        }
                        li {
                            +"IDOfResult"
                            input(InputType.text) {
                                attrs.id = "idOfResult"
                            }
                        }
                        li {
                            +"Number"
                            input(InputType.text) {
                                attrs.id = "number"
                            }
                        }
                        button {
                            +"Add"
                            attrs.onClickFunction = { props.add }
                        }
                        button {
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
    }
