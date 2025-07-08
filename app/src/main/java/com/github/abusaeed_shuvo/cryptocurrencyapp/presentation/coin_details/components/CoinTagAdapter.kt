package com.github.abusaeed_shuvo.cryptocurrencyapp.presentation.coin_details.components

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.abusaeed_shuvo.cryptocurrencyapp.data.remote.dto.Tag
import com.github.abusaeed_shuvo.cryptocurrencyapp.databinding.ItemCoinTagBinding

class CoinTagAdapter(val tagList: List<Tag>) : RecyclerView.Adapter<CoinTagAdapter.ViewHolder>() {
	override fun onCreateViewHolder(
		parent: ViewGroup,
		viewType: Int
	): ViewHolder {
		return ViewHolder(
			ItemCoinTagBinding.inflate(
				LayoutInflater.from(parent.context),
				parent,
				false
			)
		)
	}

	override fun onBindViewHolder(
		holder: ViewHolder,
		position: Int
	) {
		holder.bind(tagList[position])
	}

	override fun getItemCount(): Int = tagList.size

	inner class ViewHolder(private val binding: ItemCoinTagBinding) :
		RecyclerView.ViewHolder(binding.root) {
		fun bind(tag: Tag) = with(binding) {
			root.text = tag.name
		}
	}
}