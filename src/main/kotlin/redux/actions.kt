package redux

class AddFormula: RAction
class AddRule: RAction
class DeleteFormula(val id: Int): RAction
class DeleteRules(val id: Int): RAction
class EditFormula(val id: Int): RAction
class EditRules(val id: Int): RAction

