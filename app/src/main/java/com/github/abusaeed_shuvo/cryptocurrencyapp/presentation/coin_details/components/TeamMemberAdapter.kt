package com.github.abusaeed_shuvo.cryptocurrencyapp.presentation.coin_details.components

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.abusaeed_shuvo.cryptocurrencyapp.data.remote.dto.TeamMember
import com.github.abusaeed_shuvo.cryptocurrencyapp.databinding.ItemTeamBinding

class TeamMemberAdapter(val teamList: List<TeamMember>) :
	RecyclerView.Adapter<TeamMemberAdapter.ViewHolder>() {
	override fun onCreateViewHolder(
		parent: ViewGroup,
		viewType: Int
	): ViewHolder = ViewHolder(
		ItemTeamBinding.inflate(
			LayoutInflater.from(parent.context),
			parent,
			false
		)
	)

	override fun onBindViewHolder(
		holder: ViewHolder,
		position: Int
	) {
		holder.bind(teamList[position])
	}

	override fun getItemCount(): Int = teamList.size

	inner class ViewHolder(private val binding: ItemTeamBinding) :
		RecyclerView.ViewHolder(binding.root) {
		fun bind(teamMember: TeamMember) = with(binding) {
			name.text = teamMember.name
			position.text = teamMember.position
		}

	}
}