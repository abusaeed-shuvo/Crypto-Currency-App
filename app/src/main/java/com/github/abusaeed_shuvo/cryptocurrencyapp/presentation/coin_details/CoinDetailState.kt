package com.github.abusaeed_shuvo.cryptocurrencyapp.presentation.coin_details

import com.github.abusaeed_shuvo.cryptocurrencyapp.domain.model.CoinDetail

data class CoinDetailState(
	val isLoading: Boolean = false,
	val coin: CoinDetail? = null,
	val error: String = ""
)
