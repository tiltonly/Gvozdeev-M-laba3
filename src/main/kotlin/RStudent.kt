import data.Student
import data.studentList
import react.*
import react.dom.li
import react.dom.ol

interface  RStudentlistProps: RProps{
    var students: Array<Student>
}
interface RStudentProps : RProps {
    var student: Student
}

class RStudent : RComponent<RStudentlistProps, RState>() {
    override fun RBuilder.render() {
        ol {
            studentList.forEach {
                li {
                    rlistofstudents(it)
                }
            }
        }
    }
}

fun RBuilder.rlistofstudents(student:Student) =
    child(
        functionalComponent<RStudentProps> {
            +"${student.firstname} ${student.surname}"
        }
    ){
        attrs.student = student
    }

   fun RBuilder.studentList(students: Array<Student>)=
       child(RStudent::class){
       attrs.students = students
   }