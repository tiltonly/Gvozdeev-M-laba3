package tables

import model.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.Test
import kotlin.test.assertEquals

class MainTest {

    @Test
    fun setStoreTest(){
        Database.connect(
            "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
            driver = "org.h2.Driver"
        )
        transaction {
            SchemaUtils.create(Admins, StoreData)

            val admin = Admin.new {
                this.name = "AdminMaxim"
            }

            admin.setStore("NRS games")
            assertEquals(1, Store.count())

            SchemaUtils.drop(Admins, StoreData)
        }
    }

    @Test
    fun setGameTest(){
        Database.connect(
            "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
            driver = "org.h2.Driver"
        )
        transaction {
            SchemaUtils.create(Games, Employers)

            val employee = Employee.new {
                this.name = "Bethesda"
            }

            employee.addGame("Skyrim",
                350F, "",)

            employee.addGame("Fallout",
                150F, "",)

            assertEquals(2, Game.count())

            employee.deleteGame("Skyrim")

            assertEquals(1, Game.count())

            SchemaUtils.drop(Games, Employers)
        }
    }

    @Test
    fun setPersonTest(){
        Database.connect(
            "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
            driver = "org.h2.Driver"
        )
        transaction {
            SchemaUtils.create(Admins, Customers, Employers)

            val admin = Admin.new {
                this.name = "AdminMaxim"
            }

            admin.setPerson("Customer", "Jan")
            admin.setPerson("Customer", "Imidj")
            admin.setPerson("Employee", "Almat")
            admin.setPerson("Admin", "Zi")

            assertEquals(2, Customer.count())
            assertEquals(2, Admin.count())
            assertEquals(1, Employee.count())

            SchemaUtils.drop(Admins, Customers, Employers)
        }
    }

    @Test
    fun setPurchasesTest(){
        Database.connect(
            "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
            driver = "org.h2.Driver"
        )
        transaction {
            SchemaUtils.create(Customers, Games, PurchaseData, StoreData,CustomerGames)

            Customer.new {
                this.name = "Imidj"
                this.pocket = 0F
            }
            Game.new {
                this.name = "Resident Evil"
                this.price = 600F
                this.description = ""
            }
            Game.new {
                this.name = "CS"
                this.price = 150F
                this.description = ""
            }
            val store = Store.new { this.name = "NRS studio" }
            store.addGameToPurchases("Resident evil", "Imidj")
            store.addGameToPurchases("CS", "Imidj")
            assertEquals(1, Purchases.count())
            SchemaUtils.drop(Customers, Games,PurchaseData, StoreData,CustomerGames)
        }
    }

    @Test
    fun DeleteGameFromPurchasesTest(){
        Database.connect(
            "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
            driver = "org.h2.Driver"
        )
        transaction {
            SchemaUtils.create(Customers, Games, PurchaseData, StoreData,CustomerGames)

            Customer.new {
                this.name = "Imidj"
                this.pocket = 0F
            }
            Game.new {
                this.name = "Resident Evil"
                this.price = 600F
                this.description = ""
            }
            Game.new {
                this.name = "CS"
                this.price = 150F
                this.description = ""
            }
            val store = Store.new { this.name = "NRS studio" }

            store.addGameToPurchases("Resident evil", "Imidj")
            store.addGameToPurchases("CS", "Imidj")

            assertEquals(1, Purchases.count())

            store.deleteGameFromPurchases ("CS", "Imidj")

            assertEquals(0, Purchases.count())

            store.deleteGameFromPurchases("Resident Evil", "Imidj")
            store.addGamesToPurchases(arrayOf("Resident Evil", "CS"), "Imidj")

            assertEquals(2, Purchases.count())

            SchemaUtils.drop(Customers, Games,PurchaseData, StoreData,CustomerGames)
        }
    }

    @Test
    fun getGameTest(){
        Database.connect(
            "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
            driver = "org.h2.Driver"
        )
        transaction {
            SchemaUtils.create(Games,StoreData)
            val store = Store.new { this.name = "NRS studio" }
            val game = Game.new {
                this.name = "Resident Evil"
                this.price = 300F
                this.description = ""
            }

            assertEquals(game,store.getGameByName("Resident Evil"))

            SchemaUtils.drop(Games,StoreData)
        }
    }

    @Test
    fun buyGamesFromPurchasesTest(){
        Database.connect(
            "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
            driver = "org.h2.Driver"
        )
        transaction {
            SchemaUtils.create(Admins, Customers, Games, PurchaseData, StoreData, CustomerGames)

            val client = Customer.new {
                this.name = "Imidj"
                this.pocket = 500F
            }
            Game.new {
                this.name = "7 days to die"
                this.price = 150F
                this.description = ""
            }
            Game.new {
                this.name = "Portal 2"
                this.price = 120F
                this.description = ""
            }
            val store = Store.new { this.name = "NRS Studio"}
            store.addGamesToPurchases(arrayOf("7 days to die", "Portal 2"), "Imidj")
            store.buyGamesFromPurchases("Imidj")

            assertEquals(2, client.libraryOfGames.count())

            SchemaUtils.drop(Admins, Customers, Games, PurchaseData, StoreData, CustomerGames)
        }
    }

}