package com.github.abusaeed_shuvo.cryptocurrencyapp.di

import com.github.abusaeed_shuvo.cryptocurrencyapp.common.Constants
import com.github.abusaeed_shuvo.cryptocurrencyapp.data.remote.CoinPaprikaApi
import com.github.abusaeed_shuvo.cryptocurrencyapp.data.repository.CoinRepositoryImpl
import com.github.abusaeed_shuvo.cryptocurrencyapp.domain.repository.CoinRepository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object AppModule {

	fun providerPaprikaApi(): CoinPaprikaApi {
		return Retrofit.Builder()
			.baseUrl(Constants.BASE_URL)
			.addConverterFactory(GsonConverterFactory.create())
			.build()
			.create(CoinPaprikaApi::class.java)
	}

	fun providerCoinRepository(api: CoinPaprikaApi): CoinRepository {
		return CoinRepositoryImpl(api)
	}
}