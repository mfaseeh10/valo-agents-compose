package mfaseeh.compose.valorantagents.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity
@Serializable
internal data class AbilitiesEntity(
    @PrimaryKey(autoGenerate = true) val uuid: String = "",

    val slot: String = "",

    val displayName: String = "",

    val description: String = "",

    val displayIcon: String = ""
)