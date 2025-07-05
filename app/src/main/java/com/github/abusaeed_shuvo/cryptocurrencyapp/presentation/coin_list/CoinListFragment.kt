package com.github.abusaeed_shuvo.cryptocurrencyapp.presentation.coin_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.abusaeed_shuvo.cryptocurrencyapp.databinding.FragmentCoinListBinding
import com.github.abusaeed_shuvo.cryptocurrencyapp.presentation.coin_list.components.CoinListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinListFragment : Fragment() {
	private var _binding: FragmentCoinListBinding? = null
	private val binding get() = _binding!!
	private val viewModel: CoinListViewModel by viewModels()
	private lateinit var adapter: CoinListAdapter

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		_binding = FragmentCoinListBinding.inflate(
			inflater,
			container,
			false
		)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		adapter = CoinListAdapter { coin ->

		}

		binding.rcvCoinList.apply {
			layoutManager = LinearLayoutManager(requireContext())
			adapter = adapter
		}

		viewModel.state.observe(viewLifecycleOwner) { state ->
			adapter.submitList(state.coins)
			

		}
	}


	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}