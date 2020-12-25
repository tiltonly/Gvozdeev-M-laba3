package tables

import org.jetbrains.exposed.dao.id.IntIdTable

object Employers : IntIdTable() {
    val name = varchar("name", length = 50)

    override val primaryKey =
        PrimaryKey(id, name = "PK_EMPLOYERS_ID")
}