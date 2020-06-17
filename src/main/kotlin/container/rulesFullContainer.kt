package container

import component.RulesPageProps
import component.fRulesPage
import data.Rules
import data.State
import hoc.withDisplayName
import react.HOC
import react.RClass
import react.RProps
import react.invoke
import react.redux.rConnect
import redux.DeleteRules
import redux.EditRules
import redux.RAction
import redux.WrapperAction

interface RulesFullDispatchProps : RProps {
    var delete: (Int) -> Unit
    var edit: (Int) -> Unit
}

interface RulesFullStateProps : RProps {
    var rule: Array<Rules>
}

interface RulesFullOwnProps : RProps {
    var index: Int
}

val RulesFullHoc: HOC<RulesPageProps, RulesFullOwnProps> =
        rConnect<
                State,
                RAction,
                WrapperAction,
                RulesFullOwnProps,
                RulesFullStateProps,
                RulesFullDispatchProps,
                RulesPageProps
                >(
                mapStateToProps = {state, _ ->
                      rule = state.Rules.values.toTypedArray()
                },
                mapDispatchToProps = { dispatch, _ ->
                    delete = {dispatch(DeleteRules(it))}
                    edit = {dispatch(EditRules(it))}
                }
        )

val rulesPageRClass =
        withDisplayName(
                "rulesPage",
                fRulesPage
        )
                .unsafeCast<RClass<RulesPageProps>>()

val rulesPageContainer =
        RulesFullHoc(rulesPageRClass)