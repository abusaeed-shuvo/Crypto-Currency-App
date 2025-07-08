package com.github.abusaeed_shuvo.cryptocurrencyapp.presentation.coin_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.github.abusaeed_shuvo.cryptocurrencyapp.common.Resource
import com.github.abusaeed_shuvo.cryptocurrencyapp.domain.use_case.get_coin.GetCoinUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class CoinDetailViewModel(
	private val getCoinUseCase: GetCoinUseCase,
	private val coinId: String
) : ViewModel() {
	private val _state = MutableLiveData<CoinDetailState>()
	val state = _state

	init {
		getCoin(coinId)
	}

	private fun getCoin(coinId: String) {
		getCoinUseCase(coinId).onEach { result ->
			when (result) {
				is Resource.Success -> _state.value = CoinDetailState(coin = result.data)

				is Resource.Error   -> _state.value = CoinDetailState(
					error = result.message ?: "An unexpected error occurred"
				)


				is Resource.Loading -> _state.value = CoinDetailState(isLoading = true)

			}

		}.launchIn(viewModelScope)
	}
}

class CoinDetailViewModelFactory(
	private val getCoinUseCase: GetCoinUseCase,
	private val coinId: String
) : ViewModelProvider.Factory {
	override fun <T : ViewModel> create(modelClass: Class<T>): T {
		if (modelClass.isAssignableFrom(CoinDetailViewModel::class.java)) {
			@Suppress("UNCHECKED_CAST")
			return CoinDetailViewModel(getCoinUseCase, coinId) as T
		}
		throw IllegalArgumentException("Unknown ViewModel class")
	}
}