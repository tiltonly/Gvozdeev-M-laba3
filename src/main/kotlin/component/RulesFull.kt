package component


import data.Rules
import data.TypesOfRules
import data.typeslist
import react.*
import kotlinx.html.InputType
import kotlinx.html.id
import kotlinx.html.js.onClickFunction
import kotlinx.html.style
import org.w3c.dom.HTMLElement
import react.dom.*
import kotlin.browser.document
import kotlin.browser.window

interface RulesPageProps : RProps {
    var index: Int
    var rules: Array<Rules>
    var delete: (Int) -> Unit
    var edit: (Int) -> Unit
}

val fRulesPage =
        functionalComponent<RulesPageProps> { props ->
            div {
                div {
                    h4 { +props.rules[props.index].type }
                    p { +"ID: ${props.index}" }
                    p { +"IDOfResult: ${props.rules[props.index].idOfResult}" }
                    p { +"Number: ${props.rules[props.index].number}" }
                    p {
                        button {
                            +"Delete"
                            attrs.onClickFunction = {
                                props.delete(props.index)
                                window.history.back()
                            }
                        }
                    }
                    p {
                        button {
                            +"Edit"
                            attrs.onClickFunction = {
                                val editWindow = document
                                        .getElementById("editWindow") as HTMLElement
                                editWindow.style.display = "block"
                            }
                        }
                    }
                }
            }
            div("window") {
                attrs.id = "editWindow"
                div {
                    h1 { +"EditRules" }
                    ul {
                        li {
                            +"Type"
                            select {
                                for (element in typeslist) {
                                    attrs.id = "editedType"
                                    option {
                                        +element.type
                                    }
                                }
                                li {
                                    +"IDOfResult"
                                    input(InputType.text) {
                                        attrs.id = "editedIdOfResult"
                                        attrs.placeholder = props.rules[props.index].idOfResult.toString()
                                        attrs.defaultValue = props.rules[props.index].idOfResult.toString()
                                    }
                                }
                                li {
                                    +"Number"
                                    input(InputType.text) {
                                        attrs.id = "editedNumber"
                                        attrs.placeholder = props.rules[props.index].number.toString()
                                        attrs.defaultValue = props.rules[props.index].number.toString()
                                    }
                                }
                                button {
                                    +"Edit"
                                    attrs.onClickFunction = { props.edit(props.index) }
                                }
                                button {
                                    +"Close"
                                    attrs.onClickFunction = {
                                        val editWindow = document
                                                .getElementById("editWindow") as HTMLElement
                                        editWindow.style.display = "none"
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

