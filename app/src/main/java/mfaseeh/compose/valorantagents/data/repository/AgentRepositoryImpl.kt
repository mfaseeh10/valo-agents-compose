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
import mfaseeh.compose.valorantagents.data.remote.model.AgentResponseModel
import mfaseeh.compose.valorantagents.data.remote.source.AgentsRDS
import mfaseeh.compose.valorantagents.domain.repository.AgentRepository
import javax.inject.Inject

internal class AgentRepositoryImpl @Inject constructor (
    private val agentsRDS: AgentsRDS,
    private val agentsLDS: AgentsLDS,
    private val context: Application
) : AgentRepository {

    init {
        val appName =  context.getString(R.string.app_name)
        Log.d("Repository", "Hello from repo my name is $appName")
    }
    override suspend fun getAgents(): Flow<ResultState<List<AgentResponseModel>>> = flow {
        agentsLDS.getAgents().collect{ agents ->
            val agentLocalEmpty = agents.isEmpty()
            Log.d("Agents", "Local Result: $agentLocalEmpty")
            if(agentLocalEmpty){
                agentsRDS.fetchAgents().collect { result ->
//                    Log.d("Agents", "Result: $result")
                    if(result is ResultState.Success){
//                        Log.d("Agents", "${result.data.data}")
                        agentsLDS.reInsertAgents(result.data.data)
                        agentsLDS.getAgents()
                            .catch { emit(ResultState.Error(CustomException(it.message ?: ""))) }
                            .collect {
                                emit(ResultState.Success(it))
                            }
                    }

                    else if (result is ResultState.Error){
                        emit(result)
                        Log.e("Agents", "error from repo ${result.exception}")
                    }
                }
            } else {
                emit(ResultState.Success(agents))
            }
        }

    }

    override suspend fun getAgentDetails(uuid: String) = flow {
        agentsLDS.getAgentId(uuid)
            .catch { emit(ResultState.Error(CustomException(it.message ?: ""))) }
            .collect {
                emit(ResultState.Success(it))
            }
    }


}