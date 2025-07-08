package com.github.abusaeed_shuvo.cryptocurrencyapp.presentation.coin_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.abusaeed_shuvo.cryptocurrencyapp.R
import com.github.abusaeed_shuvo.cryptocurrencyapp.common.ServiceLocator
import com.github.abusaeed_shuvo.cryptocurrencyapp.databinding.FragmentCoinDetailsBinding
import com.github.abusaeed_shuvo.cryptocurrencyapp.domain.model.CoinDetail
import com.github.abusaeed_shuvo.cryptocurrencyapp.presentation.coin_details.components.TeamMemberAdapter
import com.google.android.material.chip.Chip

class CoinDetailsFragment : Fragment() {
	private var _binding: FragmentCoinDetailsBinding? = null
	private val binding get() = _binding!!
	private val args: CoinDetailsFragmentArgs by navArgs()

	private val viewModel: CoinDetailViewModel by viewModels {
		CoinDetailViewModelFactory(ServiceLocator.getCoinUseCase, args.coinId)
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		_binding = FragmentCoinDetailsBinding.inflate(inflater, container, false)

		// Inflate the layout for this fragment
		return binding.root
	}


	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		(requireActivity() as AppCompatActivity).supportActionBar?.title = args.coinId


		viewModel.state.observe(viewLifecycleOwner) { state ->

			if (state.coin != null) {
				loadData(state.coin)
			}

			binding.progressBar.isVisible = state.isLoading
			binding.textError.isVisible = state.error.isNotEmpty()
			binding.textError.text = state.error
		}

	}


	private fun loadData(coinDetail: CoinDetail) = with(binding) {
		coinName.text = coinDetail.name
		coinDetails.text = coinDetail.description
		coinIsActive.apply {
			text = if (coinDetail.isActive) "active" else "inactive"
			setTextColor(
				ContextCompat.getColor(
					binding.root.context,
					if (coinDetail.isActive) R.color.green else R.color.red
				)
			)
		}
		coinId.text = "ID: ${coinDetail.coinId}"
		val title = "${coinDetail.rank}. ${coinDetail.name} (${coinDetail.symbol})"
		(requireActivity() as AppCompatActivity).supportActionBar?.title = title


		val tagArray = coinDetail.tags.map { it.name }
		val teamMembers = coinDetail.team

		val teamAdapter =
			TeamMemberAdapter(teamMembers)

		tagLists.removeAllViews()

		tagArray.forEach { tag ->
			val chip = Chip(requireContext()).apply {
				text = tag
				isCheckable = false
			}
			tagLists.addView(chip)
		}




		teamList.adapter = teamAdapter
		teamList.layoutManager = LinearLayoutManager(requireContext())


	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}