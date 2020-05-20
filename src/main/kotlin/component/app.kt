package component

import data.*
import hoc.withDisplayName
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import react.*
import react.dom.*
import react.router.dom.*
import redux.*
import kotlin.browser.document

interface AppProps : RProps {
    var store: Store<State, RAction, WrapperAction>
}

interface RouteNumberResult : RProps {
    var number: String
}

fun fApp() =
    functionalComponent<AppProps> { props ->
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
                    anyList(props.store.getState().lessons, "Lessons", "/lessons")
                }
            )
            route("/students",
                exact = true,
                render = {
                    anyList(props.store.getState().students, "Students", "/students")
                }
            )
            route("/lessons/:number",
                render = renderLesson(props)
            )
            route("/students/:number",
                render = renderStudent(props)
            )
            route("/changeStudents",
                render = renChangeStudent(props)
            )
            route("/changeLessons",
                render = renChangeLesson(props)
                )
        }
    }

fun AppProps.onClickStudent(num: Int): (Int) -> (Event) -> Unit =
    { index ->
        {
            store.dispatch(ChangePresent(index, num))
        }
    }

fun AppProps.onClickLesson(num: Int): (Int) -> (Event) -> Unit =
    { index ->
        {
            store.dispatch(ChangePresent(num, index))
        }
    }

fun AppProps.Studentadd(): (Event) -> Unit ={
    val studentname = document.getElementById("Students") as HTMLInputElement
    val newname = studentname.value.split(" ")
    store.dispatch(Studentadd(newname[0],newname[1]))
}

fun AppProps.Studentdelete():(Event) -> Unit = {
    val delstudent = document.getElementById("Students") as HTMLInputElement
    store.dispatch(Studentdelete(delstudent.value.toInt()-1))
}

fun AppProps.Lessonadd(): (Event) -> Unit = {
    val newobj = document.getElementById("Lessons") as HTMLInputElement
    store.dispatch(Lessonadd(newobj.value))
}

fun AppProps.Lessondelete(): (Event) -> Unit = {
    val delobj = document.getElementById("Lessons") as HTMLInputElement
    store.dispatch(Lessondelete(delobj.value.toInt()-1))
}

fun RBuilder.renChangeLesson (props: AppProps) ={
    anyChange(
            RBuilder::AddOrDelLesson,
        RBuilder::anyList,
        props.store.getState().lessons,
        props.Lessonadd(),
        props.Lessondelete(),
        "Lessons",
        "/lessons"
    )
}

fun RBuilder.renChangeStudent(props: AppProps) = {
    anyChange(
            RBuilder::AddOrDelStudent,
        RBuilder::anyList,
        props.store.getState().students,
        props.Studentadd(),
        props.Studentdelete(),
        "Students",
        "/students"
    )
}

fun RBuilder.renderLesson(props: AppProps) =
    { route_props: RouteResultProps<RouteNumberResult> ->
        val num = route_props.match.params.number.toIntOrNull() ?: -1
        val lesson = props.store.getState().lessons.getOrNull(num)
        if (lesson != null)
            anyFull(
                RBuilder::student,
                lesson,
                props.store.getState().students,
                props.store.getState().presents[num],
                props.onClickLesson(num)
            )
        else
            p { +"No such lesson" }
    }

fun RBuilder.renderStudent(props: AppProps) =
    { route_props: RouteResultProps<RouteNumberResult> ->
        val num = route_props.match.params.number.toIntOrNull() ?: -1
        val student = props.store.getState().students.getOrNull(num)
        if (student != null)
            anyFull(
                RBuilder::lesson,
                student,
                props.store.getState().lessons,
                props.store.getState().presents.map {
                    it[num]
                }.toTypedArray(),
                props.onClickStudent(num)
            )
        else
            p { +"No such student" }
    }


fun RBuilder.app(
    store: Store<State, RAction, WrapperAction>
) =
    child(
        withDisplayName("App", fApp())
    ) {
        attrs.store = store
    }





