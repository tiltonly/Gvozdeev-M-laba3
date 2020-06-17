package component

import data.Formula
import react.*
import kotlinx.html.InputType
import kotlinx.html.id
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLElement
import react.dom.*
import kotlin.browser.document
import kotlin.browser.window

interface FormulaPageProps : RProps {
    var index: Int
    var formula: Array<Formula>
    var delete: (Int) -> Unit
    var edit: (Int) -> Unit
}

val fFormulaPage =
        functionalComponent<FormulaPageProps> { props ->
            div("card") {
                div("container") {
                    h4 { +props.formula[props.index].name }
                    p { +"ID: ${props.index}" }
                    p { +"IdOfRules: ${props.formula[props.index].idOfRule}" }
                    p {
                        button {
                            +"Delete"
                            attrs.onClickFunction = {
                                props.delete(props.index)
                                window.history.back()
                            }
                        }
                    }
                    p{
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
            div {
                attrs.id = "editWindow"
                div{
                    h1 { +"EditFormula" }
                    ul {
                        li {
                            +"Name"
                            input(InputType.text) {
                                attrs.id = "EditedName"
                                attrs.defaultValue = props.formula[props.index].name
                                attrs.placeholder = props.formula[props.index].name
                            }
                        }
                        li {
                            +"IdOfRule"
                            input(InputType.text) {
                                attrs.id = "editedIDOfRule"
                                attrs.placeholder = props.formula[props.index].idOfRule.toString()
                                attrs.defaultValue = props.formula[props.index].idOfRule.toString()
                            }
                        }
                        button{
                            +"Edit"
                            attrs.onClickFunction = {props.edit(props.index)}
                        }
                        button{
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