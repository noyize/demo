package com.noyal.demo.domain.repository

import com.noyal.demo.core.Result
import com.noyal.demo.domain.model.UserHolding
import kotlinx.coroutines.flow.Flow

interface RemoteRepository {
    suspend fun getHoldings(): Flow<Result<List<UserHolding>>>
}