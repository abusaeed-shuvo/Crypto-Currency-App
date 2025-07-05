package com.github.abusaeed_shuvo.cryptocurrencyapp.di

import com.github.abusaeed_shuvo.cryptocurrencyapp.common.Constants
import com.github.abusaeed_shuvo.cryptocurrencyapp.data.remote.CoinPaprikaApi
import com.github.abusaeed_shuvo.cryptocurrencyapp.data.repository.CoinRepositoryImpl
import com.github.abusaeed_shuvo.cryptocurrencyapp.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
	@Provides
	@Singleton
	fun providerPaprikaApi(): CoinPaprikaApi {
		return Retrofit.Builder()
			.baseUrl(Constants.BASE_URL)
			.addConverterFactory(GsonConverterFactory.create())
			.build()
			.create(CoinPaprikaApi::class.java)
	}

	@Provides
	@Singleton
	fun providerCoinRepository(api: CoinPaprikaApi): CoinRepository {
		return CoinRepositoryImpl(api)
	}
}