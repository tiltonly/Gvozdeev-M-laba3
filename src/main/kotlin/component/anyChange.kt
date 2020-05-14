 package component

import hoc.withDisplayName
import kotlinx.html.js.onClickFunction
import org.w3c.dom.events.Event
import react.*
import react.dom.*

interface anyChangeProps<O> :RProps{
    var objects : Array<O>
    var Add : (Event) -> Unit
    var Delete : (Int) -> Unit
    var name : String
    var path : String
}
fun <O> fanyChange(
    rChange :RBuilder.() -> ReactElement,
    rComponent: RBuilder.(Array<O>, String, String, (Int) -> Unit)->ReactElement
) =
    functionalComponent<anyChangeProps<O>> { props ->
        h2 { +"Page for change" }
        rChange()
        button {
             +"Add"
            attrs.onClickFunction = props.Add

        }
        rComponent(props.objects, props.name, props.path, props.Delete)
    }




fun <O> RBuilder.anyChange(
    rChange: RBuilder.() -> ReactElement,
    rComponent: RBuilder.(Array<O>, String, String, (Int) -> Unit)->ReactElement,
    objects : Array<O>,
    Add: (Event) -> Unit,
    Delete: (Int) -> Unit,
    name : String,
    path: String
    ) = child(fanyChange(rChange, rComponent)){
    attrs.objects= objects
    attrs.Add= Add
    attrs.Delete =Delete
    attrs.name = name
    attrs.path = path
}