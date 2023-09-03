package mfaseeh.compose.valorantagents.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
internal data class AgentEntity(
    @PrimaryKey val uuid: String,
    val displayName: String,
    val displayIcon: String,
)