package model

import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Employee(id: EntityID<Int>) : Person(id) {
    companion object : IntEntityClass<Employee>(tables.Employers)

    var name by tables.Employers.name

    fun addGame(name: String, price: Float, description: String){
        Game.new {
            this.name = name
            this.price = price
            this.description = description
        }
    }

    fun deleteGame(name: String) {
        Game.all().find {
            it.name == name
        }?.delete()
    }
}

