package tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.`java-time`.date

object Games: IntIdTable()  {
    var name = varchar("name", length = 50)
    var description = varchar("description", length = 50)
    var price = float("price")


    override val primaryKey =
        PrimaryKey(id, name = "PK_GAMES_ID")
}