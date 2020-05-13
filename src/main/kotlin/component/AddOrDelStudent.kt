package component

import data.Student
import hoc.withDisplayName
import kotlinx.html.InputType
import kotlinx.html.id
import react.RBuilder
import react.RProps
import react.child
import react.dom.input
import react.functionalComponent


val AddOrDelStudent =
    functionalComponent<RProps> {
        input(InputType.text) {
            attrs {
                placeholder = "For delete write a number"
                id = "Students"
            }
        }
    }

fun RBuilder.AddOrDelStudent(
) = child(
    withDisplayName("studentAdd", AddOrDelStudent)
) {}