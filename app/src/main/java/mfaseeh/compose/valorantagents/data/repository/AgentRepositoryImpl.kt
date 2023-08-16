package mfaseeh.compose.valorantagents.data.repository

import android.app.Application
import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import mfaseeh.compose.valorantagents.R
import mfaseeh.compose.valorantagents.common.ResultState
import mfaseeh.compose.valorantagents.data.model.Agent
import mfaseeh.compose.valorantagents.data.remote.source.AgentsRDS
import mfaseeh.compose.valorantagents.domain.repository.AgentRepository
import javax.inject.Inject

internal class AgentRepositoryImpl @Inject constructor(
    private val agentsRDS: AgentsRDS,
    private val context: Application
    ) : AgentRepository {

    init {
        val appName =  context.getString(R.string.app_name)
        Log.d("Repository", "Hello from repo my name is $appName")
    }
    override suspend fun getAgents(): Flow<ResultState<List<Agent>>> = flow {
        agentsRDS.fetchAgents().collect{
            if(it is ResultState.Success){
                emit(ResultState.Success(it.data.data))
            }
            else if (it is ResultState.Error){
                emit(it)
            }
        }
    }



}