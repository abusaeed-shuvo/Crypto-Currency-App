package com.github.abusaeed_shuvo.cryptocurrencyapp.presentation.coin_list.components

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.abusaeed_shuvo.cryptocurrencyapp.R
import com.github.abusaeed_shuvo.cryptocurrencyapp.databinding.ItemListCoinBinding
import com.github.abusaeed_shuvo.cryptocurrencyapp.domain.model.Coin

class CoinListAdapter(
	private val onItemClick: (Coin) -> Unit
) : ListAdapter<Coin, CoinListAdapter.CoinVH>(DiffCallBack) {
	override fun onCreateViewHolder(
		parent: ViewGroup,
		viewType: Int
	): CoinVH {
		return CoinVH(
			ItemListCoinBinding.inflate(
				LayoutInflater.from(parent.context),
				parent, false
			)
		)
	}

	override fun onBindViewHolder(
		holder: CoinVH,
		position: Int
	) {
		holder.bind(getItem(position))
	}

	inner class CoinVH(private val binding: ItemListCoinBinding) :
		RecyclerView.ViewHolder(binding.root) {
		fun bind(coin: Coin) = with(binding) {
			tvCoinTitle.text = "${coin.rank}. ${coin.name} (${coin.symbol})"
			tvCoinStatus.apply {
				text = if (coin.isActive) "active" else "inactive"
				setTextColor(
					ContextCompat.getColor(
						binding.root.context,
						if (coin.isActive) R.color.green else R.color.red
					)
				)
			}
			root.setOnClickListener {
				onItemClick(coin)
			}
		}
	}

	companion object {
		private val DiffCallBack = object : DiffUtil.ItemCallback<Coin>() {
			override fun areItemsTheSame(
				oldItem: Coin,
				newItem: Coin
			): Boolean = oldItem.id == newItem.id

			override fun areContentsTheSame(
				oldItem: Coin,
				newItem: Coin
			): Boolean = oldItem == newItem

		}
	}
}