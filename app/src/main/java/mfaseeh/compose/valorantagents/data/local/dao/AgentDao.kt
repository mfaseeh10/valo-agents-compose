package mfaseeh.compose.valorantagents.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import mfaseeh.compose.valorantagents.data.local.base.BaseDao
import mfaseeh.compose.valorantagents.data.local.entity.AgentEntity

@Dao
internal interface AgentDao: BaseDao<AgentEntity> {

    @Query("SELECT * FROM agententity WHERE uuid = :uuid")
    fun getAgentById(uuid: String): Flow<AgentEntity>

    @Query("SELECT * FROM agententity")
    fun getAllAgents(): Flow<List<AgentEntity>>

    @Query("DELETE FROM agententity")
    suspend fun deleteAgents()

    @Transaction
    suspend fun reInsertAgents(stores: List<AgentEntity>) {
        deleteAgents()
        insertAll(stores)
    }
}