package mfaseeh.compose.valorantagents.data.local.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver

fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<ValoAgentDatabase> {
    val appContext = context.applicationContext
    val dbFile = appContext.getDatabasePath("valo_agent.db")
    return Room.databaseBuilder<ValoAgentDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}

fun buildValoAgentDatabase(context: Context): ValoAgentDatabase {
    return getDatabaseBuilder(context)
        .setDriver(BundledSQLiteDriver())
        .fallbackToDestructiveMigration(dropAllTables = true)
        .build()
}