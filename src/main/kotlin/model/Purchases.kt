package model

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import tables.PurchaseData

class Purchases(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Purchases>(PurchaseData)

    var customer by Customer referencedOn PurchaseData.customer
    var game by Game referencedOn PurchaseData.game

    /*fun findGame(name: String) {
        viewingGame = gamesList.find { it.name == name }
    }

    fun findGameOnGenre(genre: Genre) {
        for(i in 0..gamesList.size)
            if (gamesList[i].genre == genre)
                viewingGame=gamesList[i]
    }

    fun addGameToPocket(game: Game){
        buyList.plus(game)
    }

    fun deleteGameFromPocket(game: Game){
        for (i in 0..buyList.size)
            if (buyList[i] == game)
                buyList.minus(i)
    }

    fun buy(clientName: String){
        for (i in 0..clientsList.size)
            if (clientsList[i].name==clientName)
                clientsList[i].library.plus(buyList)
    }

    fun cleanAll() {
        for (i in buyList.size..0)
            buyList.minus(i)
    }*/

}