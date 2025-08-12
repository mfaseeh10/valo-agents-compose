package mfaseeh.compose.valorantagents.data.local.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters
import mfaseeh.compose.valorantagents.data.local.dao.AgentDao
import mfaseeh.compose.valorantagents.data.local.entity.AgentEntity
import mfaseeh.compose.valorantagents.data.local.typeconverters.DatabaseTypeConverter

@Database(
    entities = [AgentEntity::class],
    version = 3,
    exportSchema = false
)
@TypeConverters(DatabaseTypeConverter::class)
@ConstructedBy(ValoAgentDatabaseConstructor::class)
abstract class ValoAgentDatabase : RoomDatabase() {
    abstract fun agentDao(): AgentDao
}

// The Room compiler generates the `actual` implementations.
@Suppress("KotlinNoActualForExpected")
expect object ValoAgentDatabaseConstructor : RoomDatabaseConstructor<ValoAgentDatabase> {
    override fun initialize(): ValoAgentDatabase
}