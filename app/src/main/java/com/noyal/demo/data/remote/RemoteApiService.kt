package com.noyal.demo.data.remote

import com.noyal.demo.data.remote.dto.UserHoldingResponse
import retrofit2.Response
import retrofit2.http.GET

interface RemoteApiService {
    @GET("")
    suspend fun getUserHoldings(): Response<List<UserHoldingResponse>>
}