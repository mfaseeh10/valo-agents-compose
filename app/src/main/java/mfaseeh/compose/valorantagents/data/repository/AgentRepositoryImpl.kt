package mfaseeh.compose.valorantagents.data.repository

import android.app.Application
import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import mfaseeh.compose.valorantagents.R
import mfaseeh.compose.valorantagents.common.ResultState
import mfaseeh.compose.valorantagents.common.exception.CustomException
import mfaseeh.compose.valorantagents.data.local.source.AgentsLDS
import mfaseeh.compose.valorantagents.data.model.Agent
import mfaseeh.compose.valorantagents.data.remote.source.AgentsRDS
import mfaseeh.compose.valorantagents.domain.repository.AgentRepository
import javax.inject.Inject

internal class AgentRepositoryImpl @Inject constructor(
    private val agentsRDS: AgentsRDS,
    private val agentsLDS: AgentsLDS,
    private val context: Application
    ) : AgentRepository {

    init {
        val appName =  context.getString(R.string.app_name)
        Log.d("Repository", "Hello from repo my name is $appName")
    }
    override suspend fun getAgents(): Flow<ResultState<List<Agent>>> = flow {
        agentsRDS.fetchAgents().collect { result ->
            if(result is ResultState.Success){
                agentsLDS.reInsertAgents(result.data.data)
                agentsLDS.getAgents()
                    .catch { emit(ResultState.Error(CustomException(it.message ?: ""))) }
                    .collect {
                        emit(ResultState.Success(it))
                    }
            }

            else if (result is ResultState.Error){
                emit(result)
                Log.e("Agent", "error from repo ${result.exception}")
            }
        }
    }



}