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

interface AddStudentProps: RProps{
    var Addnewstudent: (String)->Unit
}

val fAddStudent =
    functionalComponent<AddStudentProps> {props->
        h3 {
            +"Введите имя студента"
        }
        input(InputType.text) {
            attrs {
                id = "student"
            }
        }
        button {
            +"ok"
            attrs.onClickFunction = {
                val newstudentname = document.getElementById("student") as HTMLInputElement
                props.Addnewstudent(newstudentname.value)
            }
        }
    }

fun RBuilder.AddStudent(Addnewstudent: (String)-> Unit)= child(fAddStudent){
    attrs.Addnewstudent = Addnewstudent
}