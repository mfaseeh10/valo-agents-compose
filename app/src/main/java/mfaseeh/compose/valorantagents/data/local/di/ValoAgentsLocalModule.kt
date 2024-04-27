package mfaseeh.compose.valorantagents.data.local.di

import android.content.Context
import androidx.room.Room
import mfaseeh.compose.valorantagents.data.local.database.ValoAgentDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import mfaseeh.compose.valorantagents.data.local.dao.AgentDao
import mfaseeh.compose.valorantagents.data.local.source.AgentsLDS
import mfaseeh.compose.valorantagents.data.local.source.AgentsLDSImpl
import mfaseeh.compose.valorantagents.data.local.typeconverters.DatabaseTypeConverter
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ValoAgentsLocalModule {

    @Provides
    fun provideAgentsLDS(dao: AgentDao): AgentsLDS {
        return AgentsLDSImpl(dao)
    }

    @Provides
    @Singleton
    fun provideValoAgentDatabase(@ApplicationContext context: Context): ValoAgentDatabase {
        return Room.databaseBuilder(
            context,
            ValoAgentDatabase::class.java,
            ValoAgentDatabase::class.java.simpleName
        ).fallbackToDestructiveMigration() // recreate db if schema mismatch and no migration found
            .build()
    }

    @Provides
    fun provideAgentsDao(agentsDatabase: ValoAgentDatabase): AgentDao{
        return  agentsDatabase.agentDao()
    }
}