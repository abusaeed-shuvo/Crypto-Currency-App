package com.github.abusaeed_shuvo.cryptocurrencyapp.data.remote.dto


import com.github.abusaeed_shuvo.cryptocurrencyapp.domain.model.CoinDetail
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinDetailDto(
	val description: String,
	@SerialName("development_status")
	val developmentStatus: String,
	@SerialName("first_data_at")
	val firstDataAt: String,
	@SerialName("hardware_wallet")
	val hardwareWallet: Boolean,
	@SerialName("hash_algorithm")
	val hashAlgorithm: String,
	val id: String,

	val is_active: Boolean,
	@SerialName("is_new")
	val isNew: Boolean,
	@SerialName("last_data_at")
	val lastDataAt: String,
	val links: Links,
	@SerialName("links_extended")
	val linksExtended: List<LinksExtended>,
	val logo: String,
	val message: String,
	val name: String,
	@SerialName("open_source")
	val openSource: Boolean,
	@SerialName("org_structure")
	val orgStructure: String,
	@SerialName("proof_type")
	val proofType: String,
	val rank: Int,
	@SerialName("started_at")
	val startedAt: String,
	val symbol: String,
	val tags: List<Tag>,
	val team: List<TeamMember>,
	val type: String,
	val whitepaper: Whitepaper
)

fun CoinDetailDto.toCoinDetail(): CoinDetail {
	return CoinDetail(
		coinId = id,
		name = name,
		description = description,
		symbol = symbol,
		rank = rank,
		isActive = is_active,
		tags = tags,
		team = team
	)
}