package container

import component.FormulaPageProps
import component.RulesPageProps
import component.fFormulaPage
import component.fRulesPage
import data.Formula
import data.State
import hoc.withDisplayName
import react.RClass
import react.RProps
import react.invoke
import react.redux.rConnect
import redux.DeleteFormula
import redux.EditFormula
import redux.RAction
import redux.WrapperAction

interface FormulaFullDispatchProps : RProps {
    var delete: (Int) -> Unit
    var edit: (Int) -> Unit
}

interface FormulaFullStateProps : RProps {
    var formula: Array<Formula>
}

interface FormulaFullOwnProps : RProps {
    var index: Int
}

val FormulaFullHoc =
        rConnect<
                State,
                RAction,
                WrapperAction,
                FormulaFullOwnProps,
                FormulaFullStateProps,
                FormulaFullDispatchProps,
                FormulaPageProps
                >(
                mapStateToProps = {state, _ ->
                    formula = state.Formula.values.toTypedArray()
                },
                mapDispatchToProps = { dispatch, _ ->
                    delete = {dispatch(DeleteFormula(it))}
                    edit = {dispatch(EditFormula(it))}
                }
        )

val formulaPageRClass =
        withDisplayName(
                "rulesPage",
                fFormulaPage
        )
                .unsafeCast<RClass<FormulaPageProps>>()

val formulaPageContainer =
        FormulaFullHoc(formulaPageRClass)