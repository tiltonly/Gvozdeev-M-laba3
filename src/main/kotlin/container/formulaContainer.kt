package container

import component.FormulsProps
import component.RulesProps
import component.fFormula
import component.fRule
import data.FormulaState
import data.State
import hoc.withDisplayName
import react.RClass
import react.RProps
import react.invoke
import react.redux.rConnect
import redux.RAction
import redux.WrapperAction

interface FormulaDispatchProps : RProps {

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

                }
        )(
                withDisplayName(
                        "ListOfFormula",
                        fFormula())
                        .unsafeCast<RClass<FormulsProps>>()
        )