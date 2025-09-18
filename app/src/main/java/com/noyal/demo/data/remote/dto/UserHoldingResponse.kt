package com.noyal.demo.data.remote.dto


import kotlinx.serialization.Serializable

@Serializable
data class UserHoldingResponse(
    val data: Data? = null
)

@Serializable
data class Data(
    val userHolding: List<UserHoldingDto>? = null
)

@Serializable
data class UserHoldingDto(
    val symbol: String,
    val quantity: Int? = null,
    val ltp: Double? = null,
    val avgPrice: Double? = null,
    val close: Double? = null
)