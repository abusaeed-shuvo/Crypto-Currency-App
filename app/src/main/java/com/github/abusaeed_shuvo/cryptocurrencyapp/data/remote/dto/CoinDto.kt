package com.github.abusaeed_shuvo.cryptocurrencyapp.data.remote.dto


import com.github.abusaeed_shuvo.cryptocurrencyapp.domain.model.Coin
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinDto(
	val id: String,
	@SerialName("is_active") val isActive: Boolean,
	@SerialName("is_new") val isNew: Boolean,
	val name: String,
	val rank: Int,
	val symbol: String,
	val type: String
)

fun CoinDto.toCoin(): Coin {
	return Coin(
		id = id, isActive = isActive, name = name, rank = rank, symbol = symbol
	)
}