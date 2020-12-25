package tables

import org.jetbrains.exposed.dao.id.IntIdTable

object Prices: IntIdTable()  {
    val value = integer("name")
    val shortName = varchar("shortName", 50)

    override val primaryKey =
        PrimaryKey(id, name = "PK_TYPE_ID")
}