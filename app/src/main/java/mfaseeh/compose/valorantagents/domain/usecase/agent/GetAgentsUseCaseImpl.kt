package mfaseeh.compose.valorantagents.domain.usecase.agent

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import mfaseeh.compose.valorantagents.common.ResultState
import mfaseeh.compose.valorantagents.data.model.Agent
import mfaseeh.compose.valorantagents.domain.repository.AgentRepository

internal class GetAgentsUseCaseImpl(
    private val agentRepository: AgentRepository
) : GetAgentsUseCase {
    override fun invoke(): Flow<ResultState<List<Agent>>> = flow {
        agentRepository.getAgents().collect {
            Log.d("Agents", "Repo called")
            if (it is ResultState.Success) {
                Log.d("Agents", "${it.data}")
                emit(ResultState.Success(it.data))
            } else if (it is ResultState.Error) {
                emit(it)
            }
        }

    }

}