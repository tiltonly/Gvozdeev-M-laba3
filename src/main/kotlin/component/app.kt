package component

import AddStudent
import data.*
import org.w3c.dom.events.Event
import react.*
import react.dom.h1

interface AppProps : RProps {
}

interface AppState : RState {
    var students: Array<Student>
    var lessons: Array<Lesson>
    var presents: Array<Array<Boolean>>
}

class App : RComponent<AppProps, AppState>() {
    override fun componentWillMount() {
        state.students = studentList
        state.lessons = lessonsList
        state.presents = Array(state.lessons.size) {
            Array(state.students.size) { false }
        }
    }
    fun addStudent():(String) -> Unit = { student->
        val newStudent = student.split("")
        setState {
            students += Student(newStudent[0],newStudent[1])
            presents += arrayOf(Array(state.students.size){false})
        }
    }

    fun addnewlesson(): (String) -> Unit= { newlesson ->
        setState {
            lessons += Lesson(newlesson)
            presents += arrayOf(Array(state.students.size) { false })
            console.log(students)
        }
    }

    override fun RBuilder.render() {
        h1 { +"App" }
        AddStudent (addStudent())
        AddLesson (addnewlesson())
        lessonListFull(
            state.lessons,
            studentList,
            state.presents,
            onClickLessonFull
        )
        studentListFull(
            state.lessons,
            studentList,
            transform(state.presents),
            onClickStudentFull
        )
    }

    fun transform(source: Array<Array<Boolean>>) =
        Array(source[0].size){row->
            Array(source.size){col ->
                source[col][row]
            }
        }

    fun onClick(indexLesson: Int, indexStudent: Int) =
        { _: Event ->
            setState {
                presents[indexLesson][indexStudent] =
                    !presents[indexLesson][indexStudent]
            }
        }

    val onClickLessonFull =
        { indexLesson: Int ->
            { indexStudent: Int ->
                onClick(indexLesson, indexStudent)
            }
        }

    val onClickStudentFull =
        { indexStudent: Int ->
            { indexLesson: Int ->
                onClick(indexLesson, indexStudent)
            }
        }

    }


fun RBuilder.app() = child(App::class) {
}