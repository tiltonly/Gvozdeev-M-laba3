package model

import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Admin(id: EntityID<Int>) : Person(id) {
    companion object : IntEntityClass<Admin>(tables.Admins)

    var name by tables.Admins.name

    fun setStore(name: String) {
        Store.new {
            this.name = name
        }
    }

    fun setPerson(marker: String, name: String){
        when (marker) {
            "Customer" -> Customer.new {
                this.name = name
                this.pocket = 0F
            }
            "Employee" -> Employee.new {
                this.name = name
            }
            "Admin" -> Admin.new {
                this.name = name
            }
        }
    }
}