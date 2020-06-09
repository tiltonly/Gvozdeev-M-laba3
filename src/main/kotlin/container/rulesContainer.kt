package container

import component.RulesProps
import component.fRule
import data.RulesState
import data.State
import hoc.withDisplayName
import react.RClass
import react.RProps
import react.invoke
import react.redux.rConnect
import redux.RAction
import redux.WrapperAction

interface RulesDispatchProps : RProps {

}

interface RulesStateProps : RProps {
    var rule: RulesState
}

val listOfRulesContainer =
        rConnect<
                State,
                RAction,
                WrapperAction,
                RProps,
                RulesStateProps,
                RulesDispatchProps,
                RulesProps
                >(
                { state, _ ->
                    rule = state.Rules
                },
                { dispatch, _ ->

                }
        )(
                withDisplayName(
                        "ListOfRules",
                fRule())
                        .unsafeCast<RClass<RulesProps>>()
        )