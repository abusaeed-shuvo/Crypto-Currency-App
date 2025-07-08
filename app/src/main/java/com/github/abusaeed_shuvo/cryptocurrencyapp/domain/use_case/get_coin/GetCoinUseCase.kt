package com.github.abusaeed_shuvo.cryptocurrencyapp.domain.use_case.get_coin

import com.github.abusaeed_shuvo.cryptocurrencyapp.common.Resource
import com.github.abusaeed_shuvo.cryptocurrencyapp.data.remote.dto.toCoinDetail
import com.github.abusaeed_shuvo.cryptocurrencyapp.domain.model.CoinDetail
import com.github.abusaeed_shuvo.cryptocurrencyapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetCoinUseCase(
	private val repository: CoinRepository
) {

	operator fun invoke(coinId: String) = flow {
		try {
			emit(Resource.Loading<CoinDetail>())
			val coin = repository.getCoinById(coinId).toCoinDetail()
			emit(Resource.Success<CoinDetail>(coin))
		} catch (e: HttpException) {
			emit(Resource.Error<CoinDetail>(e.localizedMessage ?: "An unexpected error occurred"))
		} catch (e: IOException) {
			emit(Resource.Error<CoinDetail>("Couldn't reach server. Check your internet connection."))
		}
	}
}