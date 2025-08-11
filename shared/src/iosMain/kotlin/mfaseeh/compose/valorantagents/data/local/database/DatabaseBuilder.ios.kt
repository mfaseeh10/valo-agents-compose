package mfaseeh.compose.valorantagents.data.local.database

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

fun getDatabaseBuilder(): RoomDatabase.Builder<ValoAgentDatabase> {
    val dbFilePath = documentDirectory() + "/valo_agent.db"
    return Room.databaseBuilder<ValoAgentDatabase>(
        name = dbFilePath
    )
}

fun buildValoAgentDatabase(): ValoAgentDatabase {
    return getDatabaseBuilder()
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .fallbackToDestructiveMigration()
        .build()
}

private fun documentDirectory(): String {
    val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null,
    )
    return requireNotNull(documentDirectory?.path)
}