package com.github.abusaeed_shuvo.cryptocurrencyapp.data.remote.dto


import kotlinx.serialization.Serializable

@Serializable
data class TeamMember(
	val id: String,
	val name: String,
	val position: String
)