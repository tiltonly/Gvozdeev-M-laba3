import data.Lesson
import data.Student
import data.studentList
import org.w3c.dom.events.Event
import react.*
import react.dom.ol

interface RLessonsProps: RProps{
    var lesson: Array<Lesson>
    var listofStudents: Array<Student>
}
interface RLessonsState: RState{
    var present: Array<Boolean>
}
class RLessons: RComponent<RLessonsProps,RLessonsState>() {
    override fun componentWillMount() {
        state.apply {
            present = Array(props.listofStudents.size) { false }
        }
    }

    override fun RBuilder.render() {
        props.lesson.forEach {
            +it.namesubject
        }
        ol {
            rstudentList(props.listofStudents, state.present, onIndex())
        }
    }


    fun onIndex(): (Int) -> (Event) -> Unit = {
        onClick(it)
    }

    fun onClick(index: Int): (Event) -> Unit = {
        setState {
            present[index] = !present[index]
        }
    }
}
    fun RBuilder.rlessons(lesson: Array<Lesson>)=
        child(RLessons::class){
            attrs.lesson = lesson
            attrs.listofStudents = studentList.toTypedArray()
        }

