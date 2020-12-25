package tables

import org.jetbrains.exposed.dao.id.IntIdTable


object Customers: IntIdTable()  {
    val name = varchar("name", length = 50)
    val pocket = float("pocket")

    override val primaryKey =
        PrimaryKey(id, name = "PK_CLIENT_ID")
}