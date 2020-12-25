package model

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.SizedCollection


class Store(id: EntityID<Int>) : IntEntity(id){
    companion object : IntEntityClass<Store>(tables.StoreData)

    var name by tables.StoreData.name

    fun getGameByName(name: String) =
        Game.all().find { it.name == name }


    fun addGameToPurchases(gameName: String, customerName: String){
        val customer = Customer.all().find { it.name == customerName }?:return
        val game = Game.all().find { it.name == gameName }?:return
        if (Purchases.all().find{ it.customer == customer && it.game == game} == null)
            Purchases.new {
                this.customer = customer
                this.game = game
            }
    }

    fun addGamesToPurchases(gamesNames: Array<String>, customerName: String){
        for (i in gamesNames.indices) {
            val game = Game.all().find { it.name == gamesNames[i] }?:return
            val customer = Customer.all().find { it.name == customerName}?:return
            if (Purchases.all().find { it.customer == customer && it.game == game } == null)
                Purchases.new {
                    this.game = game
                    this.customer = customer
                }
        }
    }

    fun deleteGameFromPurchases(gameName: String, customerName: String){
        Purchases.all().find { it.game.name == gameName &&
                it.customer.name == customerName}?.delete()
    }

    fun buyGamesFromPurchases(customerName: String){
        val customer = Customer.all().find { it.name == customerName }?:return
        val purchases = Purchases.all().filter { it.customer == customer }
        for (i in purchases.indices) {
            if (customer.pocket >= purchases[i].game.price) {
                customer.pocket -= purchases[i].game.price
                customer.libraryOfGames =
                    SizedCollection( customer.libraryOfGames  + listOf(purchases[i].game))
                deleteGameFromPurchases(purchases[i].game.name, customerName)
            }
            else {
                println("You have not enough money in your valet to buy ${purchases[i].game.name}")
                break
            }
        }
    }

}