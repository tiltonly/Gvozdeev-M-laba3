package tables

import org.jetbrains.exposed.dao.id.IntIdTable


object PurchaseData: IntIdTable() {
    val customer = reference("Customer", Customers)
    val game = reference("Game", Games)

    override val primaryKey =
        PrimaryKey(id, name = "PK_PURCHASE_ID")
}