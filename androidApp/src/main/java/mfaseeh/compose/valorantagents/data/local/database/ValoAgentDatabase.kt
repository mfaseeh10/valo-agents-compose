package mfaseeh.compose.valorantagents.data.local.database
import androidx.room.Database
import androidx.room.RoomDatabase
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
internal abstract class ValoAgentDatabase : RoomDatabase() {
    abstract fun agentDao(): AgentDao
}
