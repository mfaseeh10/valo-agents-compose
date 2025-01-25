package mfaseeh.compose.valorantagents.data.repository

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import mfaseeh.compose.valorantagents.common.ResultState
import mfaseeh.compose.valorantagents.common.exception.CustomException
import mfaseeh.compose.valorantagents.data.local.source.AgentsLDS
import mfaseeh.compose.valorantagents.data.remote.model.AgentResponseModel
import mfaseeh.compose.valorantagents.data.remote.source.AgentsRDS
import mfaseeh.compose.valorantagents.domain.repository.AgentRepository

internal class AgentRepositoryImpl (
    private val agentsRDS: AgentsRDS,
    private val agentsLDS: AgentsLDS
) : AgentRepository {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getAgents(): Flow<ResultState<List<AgentResponseModel>>> =
        agentsLDS.getAgents()
            .flatMapConcat { agents ->
                if (agents.isEmpty()) {
                    agentsRDS.fetchAgents()
                        .flatMapConcat { result ->
                            when (result) {
                                is ResultState.Success -> {
                                    agentsLDS.reInsertAgents(result.data.data)
                                    agentsLDS.getAgents()
                                        .map { ResultState.Success(it) }
                                }
                                is ResultState.Error -> flowOf(result)
                            }
                        }
                } else {
                    flowOf(ResultState.Success(agents))
                }
            }
            .catch { emit(ResultState.Error(CustomException(it.message ?: ""))) }


    override fun getAgentDetails(uuid: String) =
        agentsLDS.getAgentId(uuid)
            .catch { ResultState.Error(CustomException(it.message ?: "")) }
            .map {
                ResultState.Success(it)
            }



}