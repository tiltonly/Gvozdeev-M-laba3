package data

data class Student (
    val firstname: String,
    val surname: String
)

val studentList =
    arrayListOf(
        Student("Ryan", "Cooper"),
        Student("Corey", "Taylor"),
        Student("Maxim", "Gvozdeev")
    )