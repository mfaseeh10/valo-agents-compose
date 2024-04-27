package mfaseeh.compose.valorantagents.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity
@Serializable
internal data class RoleEntity(
    @PrimaryKey val uuid: String = "",

    val displayIcon: String = "",

    val displayName: String = "",

    val assetPath: String = "",

    val description: String = ""
)
