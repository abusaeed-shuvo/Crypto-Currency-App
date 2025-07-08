package com.github.abusaeed_shuvo.cryptocurrencyapp.presentation.coin_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.abusaeed_shuvo.cryptocurrencyapp.common.ServiceLocator
import com.github.abusaeed_shuvo.cryptocurrencyapp.databinding.FragmentCoinListBinding
import com.github.abusaeed_shuvo.cryptocurrencyapp.presentation.coin_list.components.CoinListAdapter


class CoinListFragment : Fragment() {
	private var _binding: FragmentCoinListBinding? = null
	private val binding get() = _binding!!
	private val viewModel: CoinListViewModel by viewModels {
		CoinListViewModelFactory(ServiceLocator.getCoinsUseCase)
	}
	private lateinit var coinListAdapter: CoinListAdapter

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View? {
		_binding = FragmentCoinListBinding.inflate(
			inflater, container, false
		)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		(requireActivity() as AppCompatActivity).supportActionBar?.title = "Coin List"

		coinListAdapter = CoinListAdapter { coin ->

			val action =
				CoinListFragmentDirections.actionCoinListFragmentToCoinDetailsFragment(coinId = coin.id)
			findNavController().navigate(action)
		}

		binding.rcvCoinList.apply {
			layoutManager = LinearLayoutManager(requireContext())
			adapter = coinListAdapter
		}

		viewModel.state.observe(viewLifecycleOwner) { state ->
			coinListAdapter.submitList(state.coins)

			binding.progressBar.isVisible = state.isLoading
			binding.textError.isVisible = state.error.isNotEmpty()
			binding.textError.text = state.error

		}
	}


	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}