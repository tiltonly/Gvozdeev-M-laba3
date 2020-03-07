import data.Student
import data.studentList
import kotlinx.html.*
import kotlinx.html.dom.append
import kotlinx.html.js.*
import kotlinx.html.js.li
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLLabelElement
import org.w3c.dom.events.Event
import kotlin.browser.document
import kotlin.dom.clear

var ascending = true

fun main() {
    document.getElementById("root")!!
        .append {
            h1 {
                attributes += "id" to "header"
                +"Students"
                onClickFunction = onCLickFunction()
            }
            ol {
                attributes += "id" to "listStudents"
                studentList.map {
                    li {
                        attributes += "id" to it.firstname
                        +"${it.firstname} ${it.surname}"
                        onClickFunction = presenceOrNotFun(it)
                    }
                }
            }
            input( options = arrayListOf("red","black","green"))
        }
}

private fun H1.onCLickFunction(): (Event) -> Unit {
    return {
        val listStudents = document.getElementById("listStudents")!!
        listStudents.clear()
        listStudents.append {
            if (ascending)
                studentList.sortBy { it.firstname }
            else
                studentList.sortByDescending { it.firstname }
            ascending = !ascending

            studentList.map {
                li {
                    attributes += "id" to it.firstname
                    +"${it.firstname} ${it.surname}"
                    onClickFunction = presenceOrNotFun(it)
                    if(!it.presence)
                        attributes+= "style" to "color:grey"
                }
            }
        }
    }
}

fun TagConsumer<HTMLElement>.input(
    classes : String? = null,
    options: List<String>,
    block : LABEL.() -> Unit = {}
): HTMLLabelElement = label(classes) {
        options.forEach {
            +it
            input(InputType.radio, name = "Colors") {
                value = it
                onClickFunction = {
                    val color =
                        document.getElementById("root")!!
                    color.setAttribute("style", "color:$value")
                }
            }
        }
        block()
    }

private fun LI.presenceOrNotFun(Student :Student): (Event) -> Unit {
    return{
        val student = document.getElementById(Student.firstname)!!
        if (Student.presence) {
            student.setAttribute("style", "color:grey")
            Student.presence = false
        }
        else {
            student.setAttribute("style", "color:white")
            Student.presence = true
        }
    }
}


