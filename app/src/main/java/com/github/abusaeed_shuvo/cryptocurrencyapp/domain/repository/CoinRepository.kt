package com.github.abusaeed_shuvo.cryptocurrencyapp.domain.repository

import com.github.abusaeed_shuvo.cryptocurrencyapp.data.remote.dto.CoinDetailDto
import com.github.abusaeed_shuvo.cryptocurrencyapp.data.remote.dto.CoinDto

interface CoinRepository {

	suspend fun getCoins(): List<CoinDto>

	suspend fun getCoinById(coinId: String): CoinDetailDto

}