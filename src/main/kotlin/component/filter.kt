package component

import container.filterLink
import data.VisibilityFilter
import react.RBuilder
import react.dom.div
import react.dom.span

fun RBuilder.footer() =
        div {
            span { +"Filter: " }
            filterLink {
                attrs.filter = VisibilityFilter.SHOW_ALL
                +"All"
            }
            filterLink {
                attrs.filter = VisibilityFilter.SHOW_ABSENT
                +"Absent"
            }
            filterLink {
                attrs.filter = VisibilityFilter.SHOW_PRESENT
                +"Present"
            }
        }