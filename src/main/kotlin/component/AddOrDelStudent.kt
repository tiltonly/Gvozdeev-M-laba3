package component

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
                placeholder = "Write a new student"
                id = "Students"
            }
        }
    }

fun RBuilder.AddOrDelStudent(
) = child(
    withDisplayName("studentAdd", AddOrDelStudent)
) {}
