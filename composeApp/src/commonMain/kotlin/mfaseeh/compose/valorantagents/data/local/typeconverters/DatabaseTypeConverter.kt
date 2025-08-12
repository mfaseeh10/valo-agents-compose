package mfaseeh.compose.valorantagents.data.local.typeconverters

import androidx.room.TypeConverter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import mfaseeh.compose.valorantagents.data.local.entity.AbilitiesEntity
import mfaseeh.compose.valorantagents.data.local.entity.RoleEntity

class DatabaseTypeConverter {

    private val json = Json

    @TypeConverter
    fun convertRoleToString(role: RoleEntity): String =
        json.encodeToString(role)

    @TypeConverter
    fun convertStringToRole(string: String): RoleEntity? = json.decodeFromString(string)

    @TypeConverter
    fun convertAbilitiesToString(abilitiesEntity: List<AbilitiesEntity>): String =
        json.encodeToString(abilitiesEntity)

    @TypeConverter
    fun convertStringToAbilities(string: String): List<AbilitiesEntity>? = json.decodeFromString(string)
}