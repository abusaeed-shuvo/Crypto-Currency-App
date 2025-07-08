package com.github.abusaeed_shuvo.cryptocurrencyapp.data.repository

import com.github.abusaeed_shuvo.cryptocurrencyapp.data.remote.CoinPaprikaApi
import com.github.abusaeed_shuvo.cryptocurrencyapp.data.remote.dto.CoinDetailDto
import com.github.abusaeed_shuvo.cryptocurrencyapp.data.remote.dto.CoinDto
import com.github.abusaeed_shuvo.cryptocurrencyapp.domain.repository.CoinRepository

class CoinRepositoryImpl(
	private val api: CoinPaprikaApi
) : CoinRepository {
	override suspend fun getCoins(): List<CoinDto> =
		api.getCoins()


	override suspend fun getCoinById(coinId: String): CoinDetailDto =
		api.getCoinById(coinId)

}