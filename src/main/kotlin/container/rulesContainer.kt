package container

import component.RulesProps
import component.fRule
import data.Rules
import data.RulesState
import data.State
import hoc.withDisplayName
import org.w3c.dom.events.Event
import react.HOC
import react.RClass
import react.RProps
import react.invoke
import react.redux.rConnect
import redux.AddRule
import redux.RAction
import redux.WrapperAction

interface RulesDispatchProps : RProps {
    var add: (Event) -> Unit
}

interface RulesStateProps : RProps {
    var rule: Array<Rules>
}

val listOfRulesContainerHOC =
        rConnect<
                State,
                RAction,
                WrapperAction,
                RProps,
                RulesStateProps,
                RulesDispatchProps,
                RulesProps
                >(
                mapStateToProps =
                { state, _ ->
                    rule = state.Rules.values.toTypedArray()
                },
                mapDispatchToProps =
                { dispatch, _ ->
                    add = {dispatch(AddRule())}
                }
        )
 val listofRulesRClass =
             withDisplayName(
                     "ListOfRules",
                     fRule
             )
                     .unsafeCast<RClass<RulesProps>>()

val listOfRulesContainer = listOfRulesContainerHOC(listofRulesRClass)