package container

import component.FormulsProps
import component.RulesProps
import component.fFormula
import component.fRule
import data.FormulaState
import data.State
import hoc.withDisplayName
import org.w3c.dom.events.Event
import react.RClass
import react.RProps
import react.invoke
import react.redux.rConnect
import redux.AddFormula
import redux.RAction
import redux.WrapperAction

interface FormulaDispatchProps : RProps {
    var add: (Event) -> Unit
}

interface FormulaStateProps : RProps {
    var formula: FormulaState
}

val listOfFormulaContainer =
        rConnect<
                State,
                RAction,
                WrapperAction,
                RProps,
                FormulaStateProps,
                FormulaDispatchProps,
                FormulsProps
                >(
                { state, _ ->
                    formula = state.Formula
                },
                { dispatch, _ ->
                    add = {dispatch(AddFormula())}
                }
        )(
                withDisplayName(
                        "ListOfFormula",
                        fFormula())
                        .unsafeCast<RClass<FormulsProps>>()
        )