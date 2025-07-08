package com.github.abusaeed_shuvo.cryptocurrencyapp.common

import com.github.abusaeed_shuvo.cryptocurrencyapp.data.remote.CoinPaprikaApi
import com.github.abusaeed_shuvo.cryptocurrencyapp.data.repository.CoinRepositoryImpl
import com.github.abusaeed_shuvo.cryptocurrencyapp.domain.repository.CoinRepository
import com.github.abusaeed_shuvo.cryptocurrencyapp.domain.use_case.get_coin.GetCoinUseCase
import com.github.abusaeed_shuvo.cryptocurrencyapp.domain.use_case.get_coins.GetCoinsUseCase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceLocator {

	private val retrofit: Retrofit = Retrofit.Builder()
		.baseUrl(Constants.BASE_URL)
		.addConverterFactory(GsonConverterFactory.create())
		.build()

	val paprikaApi: CoinPaprikaApi = retrofit.create(CoinPaprikaApi::class.java)

	val repository: CoinRepository = CoinRepositoryImpl(paprikaApi)

	val getCoinUseCase = GetCoinUseCase(repository)
	val getCoinsUseCase = GetCoinsUseCase(repository)

}