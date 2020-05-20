package component

import hoc.withDisplayName
import kotlinx.html.js.onClickFunction
import org.w3c.dom.events.Event
import react.*
import react.dom.*

interface anyChangeProps<O> :RProps{
    var objects : Array<O>
    var Add : (Event) -> Unit
    var Delete : (Event) -> Unit
    var name : String
    var path : String
}
fun <O> fanyChange(
        rChange :RBuilder.() -> ReactElement,
        rComponent: RBuilder.(Array<O>, String, String)->ReactElement
) =
        functionalComponent<anyChangeProps<O>> { props ->
            h2 { +"Page for change" }
            rChange()
            button {
                +"Add"
                attrs.onClickFunction = props.Add

            }
            button {
                +"Delete"
                attrs.onClickFunction = props.Delete
            }
            rComponent(props.objects, props.name, props.path)
        }




fun <O> RBuilder.anyChange(
        rChange: RBuilder.() -> ReactElement,
        rComponent: RBuilder.(Array<O>, String, String)->ReactElement,
        objects : Array<O>,
        Add: (Event) -> Unit,
        Delete: (Event) -> Unit,
        name : String,
        path: String
) = child(withDisplayName("anyChange",fanyChange<O>(rChange,rComponent))){
    attrs.objects= objects
    attrs.Add= Add
    attrs.Delete =Delete
    attrs.name = name
    attrs.path = path
}