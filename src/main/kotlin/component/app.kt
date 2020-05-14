package component

import data.*
import hoc.withDisplayName
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import react.*
import react.dom.*
import react.router.dom.*
import kotlin.browser.document
import kotlin.reflect.KClass

interface AppProps : RProps {}

interface AppState : RState {
    var lessons: Array<Lesson>
    var students: Array<Student>
    var presents: Array<Array<Boolean>>
}

interface RouteNumberResult : RProps {
    var number: String
}

class App : RComponent<AppProps, AppState>() {
    override fun componentWillMount() {
        state.lessons = lessonsList
        state.students = studentList
        state.presents = Array(state.lessons.size) {
            Array(state.students.size) { false }
        }

    }

    override fun RBuilder.render() {
        header {
            h1 { +"App" }
            nav {
                ul {
                    li { navLink("/lessons") { +"Lessons" } }
                    li { navLink("/students") { +"Students" } }
                    li {navLink("/changeStudents"){+"Change Students"} }
                    li {navLink("/changeLessons"){+"Change Lessons"} }
                }
            }
        }

        switch {
            route("/lessons",
                exact = true,
                render = {
                    anyList(state.lessons, "Lessons", "/lessons",Lessondelete())
                }
            )
            route("/students",
                exact = true,
                render = {
                    anyList(state.students, "Students", "/students", Studentdelete())
                }
            )
            route("/lessons/:number",
                render = { route_props: RouteResultProps<RouteNumberResult> ->
                    val num = route_props.match.params.number.toIntOrNull() ?: -1
                    val lesson = state.lessons.getOrNull(num)
                    if (lesson != null)
                        anyFull(
                            RBuilder::student,
                            lesson,
                            state.students,
                            state.presents[num]
                        ) { onClick(num, it) }
                    else
                        p { +"No such lesson" }
                }
            )
            route("/students/:number",
                render = { route_props: RouteResultProps<RouteNumberResult> ->
                    val num = route_props.match.params.number.toIntOrNull() ?: -1
                    val student = state.students.getOrNull(num)
                    if (student != null)
                        anyFull(
                            RBuilder::lesson,
                            student,
                            state.lessons,
                            state.presents.map {
                                it[num]
                            }.toTypedArray()
                        ) { onClick(it, num) }
                    else
                        p { +"No such student" }
                }
            )
            route("/changeStudents",
                exact = true,
            render = {
                anyChange(
                    RBuilder::AddOrDelStudent,
                    RBuilder::anyList,
                    state.students,
                    Studentadd(),
                    Studentdelete(),
                    "Students",
                    "/students"
                )
            }
            )
            route("/changeLessons",
                exact = true,
            render = {
                anyChange(
                    RBuilder::AddOrDelLesson,
                    RBuilder::anyList,
                    state.lessons,
                    Lessonadd(),
                    Lessondelete(),
                    "Lessons",
                    "/lessons"
                )
            })
        }
    }

    fun onClick(indexLesson: Int, indexStudent: Int) =
        { _: Event ->
            setState {
                presents[indexLesson][indexStudent] =
                    !presents[indexLesson][indexStudent]
            }
        }

    fun Lessonadd():(Event) -> Unit = {
        val nameObj = document.getElementById("Lessons") as HTMLInputElement
        val editedLesson = state.presents.mapIndexed { index, _ ->
            state.presents[index].plus(arrayOf(false))
        }
        setState {
            lessons += Lesson(nameObj.value)
            presents = editedLesson.toTypedArray()
        }
    }

    fun Lessondelete() :(Int) -> Unit = {
        val editedLessons = state.lessons.toMutableList().apply {
            removeAt(it) }
        val editedPresents = state.presents.mapIndexed { index, _ ->
            state.presents[index].toMutableList().apply {
                removeAt(it)
            }.toTypedArray()
        }
        setState{
            lessons = editedLessons.toTypedArray()
            presents= editedPresents.toTypedArray()
        }
    }

    fun Studentadd():(Event) -> Unit = {
        val nameObj = document.getElementById("Students") as HTMLInputElement
        val newStr = nameObj.value.split(" ")
        setState {
            students += Student(newStr[0],newStr[1])
            presents += arrayOf(Array(state.students.size){false})
        }
    }

    fun Studentdelete() :(Int) -> Unit = {
        val editedStudents = state.students.toMutableList().apply {
            removeAt(it)}
        val editedPresents = state.presents.toMutableList().apply {
            removeAt(it)}
        setState{
            students = editedStudents.toTypedArray()
            presents= editedPresents.toTypedArray()
        }
    }
}

fun RBuilder.app() =
    child(
        withDisplayName("AppHoc", App::class)
    ) {
    }





