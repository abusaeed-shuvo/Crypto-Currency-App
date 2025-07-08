package com.github.abusaeed_shuvo.cryptocurrencyapp.domain.use_case.get_coins

import com.github.abusaeed_shuvo.cryptocurrencyapp.common.Resource
import com.github.abusaeed_shuvo.cryptocurrencyapp.data.remote.dto.toCoin
import com.github.abusaeed_shuvo.cryptocurrencyapp.domain.model.Coin
import com.github.abusaeed_shuvo.cryptocurrencyapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException


class GetCoinsUseCase(
	private val repository: CoinRepository
) {
	operator fun invoke() = flow {
		try {
			emit(Resource.Loading<List<Coin>>())
			val coins = repository.getCoins().map { it.toCoin() }
			emit(Resource.Success<List<Coin>>(coins))
		} catch (e: HttpException) {
			emit(Resource.Error<List<Coin>>(e.localizedMessage ?: "An unexpected error occurred"))
		} catch (e: IOException) {
			emit(Resource.Error<List<Coin>>("Couldn't reach server. Check your internet connection"))
		}
	}
}