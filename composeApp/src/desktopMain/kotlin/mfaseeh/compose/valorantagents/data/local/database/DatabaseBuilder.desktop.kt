package mfaseeh.compose.valorantagents.data.local.database

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import java.io.File

fun getDatabaseBuilder(): RoomDatabase.Builder<ValoAgentDatabase> {
    val dbFile = File(System.getProperty("java.io.tmpdir"), "valo_agent.db")
    return Room.databaseBuilder<ValoAgentDatabase>(
        name = dbFile.absolutePath
    )
}

fun buildValoAgentDatabase(): ValoAgentDatabase {
    return getDatabaseBuilder()
        .setDriver(BundledSQLiteDriver())
        .fallbackToDestructiveMigration(dropAllTables = true)
        .build()
}
