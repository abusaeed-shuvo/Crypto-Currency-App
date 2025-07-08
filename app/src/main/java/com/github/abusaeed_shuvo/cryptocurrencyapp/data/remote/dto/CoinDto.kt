package com.github.abusaeed_shuvo.cryptocurrencyapp.data.remote.dto


import com.github.abusaeed_shuvo.cryptocurrencyapp.domain.model.Coin
import kotlinx.serialization.Serializable

@Serializable
data class CoinDto(
	val id: String,
	val is_active: Boolean,
	val is_new: Boolean,
	val name: String,
	val rank: Int,
	val symbol: String,
	val type: String
)

fun CoinDto.toCoin(): Coin {
	return Coin(
		id = id, isActive = is_active, name = name, rank = rank, symbol = symbol
	)
}