package model

import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import tables.CustomerGames


class Customer (id: EntityID<Int>) : Person(id) {
    companion object : IntEntityClass<Customer>(tables.Customers)

    var name by tables.Customers.name
    var pocket by tables.Customers.pocket

    var libraryOfGames by Game via CustomerGames
}