package tables

import org.jetbrains.exposed.dao.id.IntIdTable

object Genres: IntIdTable()  {
    val name = varchar("name", 50)
    val shortName = varchar("shortName", 50)

    override val primaryKey =
        PrimaryKey(id, name = "PK_TYPE_ID")
}