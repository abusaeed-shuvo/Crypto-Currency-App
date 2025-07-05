package com.github.abusaeed_shuvo.cryptocurrencyapp.presentation.coin_list

import com.github.abusaeed_shuvo.cryptocurrencyapp.domain.model.Coin

data class CoinListState(
	val isLoading: Boolean = false,
	val coins: List<Coin> = emptyList<Coin>(),
	val error: String = ""
)