package tables

import org.jetbrains.exposed.dao.id.IntIdTable

object CustomerGames: IntIdTable() {
    val game = reference("Game", Games)
    val customer = reference("Client", Customers)

    override  val primaryKey =
        PrimaryKey(customer, game, name = "PK_STORE_GAMES_ID")
}