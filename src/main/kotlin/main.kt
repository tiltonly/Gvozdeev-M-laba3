import model.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import tables.*
import tables.PurchaseData

fun main() {
    Database.connect(
            "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
            driver = "org.h2.Driver"
    )

    transaction {
        SchemaUtils.create(Admins, StoreData, PurchaseData, Customers, Games,  Genres)

        init()

        SchemaUtils.drop(Admins, StoreData, PurchaseData, Customers, Games,  Genres)
    }
}

fun init() {

}