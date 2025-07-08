package com.github.abusaeed_shuvo.cryptocurrencyapp.domain.model

import com.github.abusaeed_shuvo.cryptocurrencyapp.data.remote.dto.Tag
import com.github.abusaeed_shuvo.cryptocurrencyapp.data.remote.dto.TeamMember

data class CoinDetail(
	val coinId: String,
	val name: String,
	val description: String,
	val symbol: String,
	val rank: Int,
	val isActive: Boolean,
	val tags: List<Tag>,
	val team: List<TeamMember>
)
