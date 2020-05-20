package redux

class ChangePresent(val lesson: Int, val student: Int) : RAction
class Studentadd (val Name: String, val Surname: String) :RAction
class Studentdelete (val delstudent: Int) :RAction
class Lessonadd (val lesson: String) :RAction
class Lessondelete (val dellesson: Int) :RAction