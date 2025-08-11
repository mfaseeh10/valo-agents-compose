package mfaseeh.compose.valorantagents.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AgentEntity(
    @PrimaryKey val uuid: String,
    val displayName: String,
    val displayIcon: String,
    val isPlayableCharacter: Boolean,
    val description: String,
    val fullPortrait: String? = "",
    val fullPortraitV2: String? = "",
    val background: String? = "",
    val roleEntity: RoleEntity,
    val abilitiesEntity: List<AbilitiesEntity>
)