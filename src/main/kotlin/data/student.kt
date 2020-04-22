package data

data class Student (
    val firstname: String,
    val surname: String
)

val studentList =
    arrayOf(
        Student("Ryan", "Cooper"),
        Student("Corey", "Taylor"),
        Student("Maxim", "Gvozdeev")
    )