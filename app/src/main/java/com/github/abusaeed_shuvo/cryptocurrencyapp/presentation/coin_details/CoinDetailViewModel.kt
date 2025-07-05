package com.github.abusaeed_shuvo.cryptocurrencyapp.presentation.coin_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.abusaeed_shuvo.cryptocurrencyapp.common.Constants
import com.github.abusaeed_shuvo.cryptocurrencyapp.common.Resource
import com.github.abusaeed_shuvo.cryptocurrencyapp.domain.use_case.get_coin.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class CoinDetailViewModel @Inject constructor(
	private val getCoinUseCase: GetCoinUseCase,
	savedStateHandle: SavedStateHandle
) : ViewModel() {
	private val _state = MutableLiveData<CoinDetailState>()
	val state = _state

	init {
		savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinId ->
			getCoin(coinId)
		}

	}

	private fun getCoin(coinId: String) {
		getCoinUseCase(coinId).onEach { result ->
			when (result) {
				is Resource.Success -> {
					_state.value = CoinDetailState(coin = result.data)
				}

				is Resource.Error   -> {

					_state.value = CoinDetailState(
						error = result.message ?: "An unexpected error occurred"
					)
				}

				is Resource.Loading -> {
					_state.value = CoinDetailState(isLoading = true)
				}
			}

		}.launchIn(viewModelScope)
	}
}