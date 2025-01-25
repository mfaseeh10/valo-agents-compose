package mfaseeh.compose.valorantagents.data.local.di

import android.content.Context
import androidx.room.Room
import mfaseeh.compose.valorantagents.data.local.database.ValoAgentDatabase
import mfaseeh.compose.valorantagents.data.local.source.AgentsLDS
import mfaseeh.compose.valorantagents.data.local.source.AgentsLDSImpl
import org.koin.dsl.module

val valoAgentsLocalModule = module {

    // Provide the Room database
    single { (context: Context) ->
        Room.databaseBuilder(
            context,
            ValoAgentDatabase::class.java,
            ValoAgentDatabase::class.java.simpleName
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    // Provide the DAO
    factory {
        val database: ValoAgentDatabase = get()
        database.agentDao()
    }

    // Provide the AgentsLDS implementation
    factory<AgentsLDS> {
        AgentsLDSImpl(get())
    }
}