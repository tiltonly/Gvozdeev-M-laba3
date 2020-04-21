package component

import kotlinx.html.InputType
import kotlinx.html.id
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLInputElement
import react.RBuilder
import react.RProps
import react.child
import react.dom.button
import react.dom.h3
import react.dom.input
import react.functionalComponent
import kotlin.browser.document

interface AddLessonProps: RProps{
    var Addnewlesson: (String)->Unit
}

val fAddLesson =
    functionalComponent<AddLessonProps> {props->
        h3 {
            +"Введите название нужного вам урока"
        }
        input(InputType.text) {
            attrs {
                id = "newlesson"
            }
        }
        button {
            +"ok"
            attrs.onClickFunction = {
                val newlessonname = document.getElementById("newlesson") as HTMLInputElement
                props.Addnewlesson(newlessonname.value)
            }
        }
    }

fun RBuilder.AddLesson(Addnewlesson: (String)-> Unit)= child(fAddLesson){
    attrs.Addnewlesson = Addnewlesson
}



