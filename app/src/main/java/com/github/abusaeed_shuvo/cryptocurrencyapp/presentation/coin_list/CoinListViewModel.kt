package com.github.abusaeed_shuvo.cryptocurrencyapp.presentation.coin_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.abusaeed_shuvo.cryptocurrencyapp.common.Resource
import com.github.abusaeed_shuvo.cryptocurrencyapp.domain.use_case.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class CoinListViewModel @Inject constructor(
	private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {

	private val _state = MutableLiveData<CoinListState>()
	val state = _state

	init {
		getCoins()
	}

	private fun getCoins() {
		getCoinsUseCase().onEach { result ->
			when (result) {
				is Resource.Success -> {
					_state.value = CoinListState(coins = result.data ?: emptyList())
				}

				is Resource.Error   -> {

					_state.value = CoinListState(
						error = result.message ?: "An unexpected error occurred"
					)
				}

				is Resource.Loading -> {
					_state.value = CoinListState(isLoading = true)
				}
			}

		}.launchIn(viewModelScope)
	}

}