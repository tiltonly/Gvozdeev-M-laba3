package component

import hoc.withDisplayName
import kotlinx.html.InputType
import kotlinx.html.id
import react.*
import react.dom.*


val  AddOrDelLesson =
        functionalComponent<RProps> {
            input(InputType.text) {
                attrs {
                    placeholder = "For delete write a number"
                    id = "Lessons"
                }
            }
        }

fun RBuilder.AddOrDelLesson(
) = child(
        withDisplayName("lessonAdd", AddOrDelLesson)
) {}