package data

data class Student (
    val firstname: String,
    val surname: String,
    var presence: Boolean
)
val studentList =
    arrayListOf(
        Student("Ryan", "Cooper",true),
        Student("Corey", "Taylor",true),
        Student("Maxim", "Gvozdeev",true)
    )