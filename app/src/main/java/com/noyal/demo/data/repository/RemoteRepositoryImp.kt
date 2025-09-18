package com.noyal.demo.data.repository

import com.noyal.demo.core.Result
import com.noyal.demo.core.safeCall
import com.noyal.demo.data.mapper.toDomain
import com.noyal.demo.data.remote.RemoteApiService
import com.noyal.demo.data.remote.dto.UserHoldingResponse
import com.noyal.demo.domain.model.UserHolding
import com.noyal.demo.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteRepositoryImp @Inject constructor(
    private val remoteApiService: RemoteApiService
) : RemoteRepository {

    override suspend fun getHoldings(): Flow<Result<List<UserHolding>>> {
        return safeCall<List<UserHolding>, UserHoldingResponse>(
            call = { remoteApiService.getUserHoldings() },
            mapper = { data -> data.data?.userHolding.orEmpty().map { dto -> dto.toDomain() } }
        )
    }
}
