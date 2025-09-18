package com.noyal.demo.data.mapper

import com.noyal.demo.data.remote.dto.UserHoldingDto
import com.noyal.demo.domain.model.UserHolding

fun UserHoldingDto.toDomain(): UserHolding {
    return UserHolding(
        symbol = symbol,
        quantity = quantity ?: 0,
        ltp = ltp ?: 0.0,
        avgPrice = avgPrice ?: 0.0,
        close = close ?: 0.0
    )
}