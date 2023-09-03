package mfaseeh.compose.valorantagents.data.local.database
import androidx.room.Database
import androidx.room.RoomDatabase
import mfaseeh.compose.valorantagents.data.local.dao.AgentDao
import mfaseeh.compose.valorantagents.data.local.entity.AgentEntity

@Database(
    entities = [AgentEntity::class],
    version = 1,
    exportSchema = false
)

internal abstract class ValoAgentDatabase : RoomDatabase() {
    abstract fun agentDao(): AgentDao
}
